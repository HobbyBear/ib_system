/**
 * @Title: NFCUtil.java
 * @Package com.bus.utils
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0
 */
package com.bus.utils;

import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: NFCUtil
 * @Description: NFC卡操作工具类
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class NFCUtil {

    /**
     * @Fields LOGGER : 日志对象
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(NFCUtil.class);

    /**
     * @Fields CLS_NFC : 读卡器cls值
     */
    private static final byte CLS_NFC = (byte) 0xFF;
    /**
     * @Fields INS_READ : 读卡器读卡时的ins值
     */
    private static final byte INS_READ = (byte) 0xB0;
    /**
     * @Fields INS_WRITE : 读卡器写卡时的ins值
     */
    private static final byte INS_WRITE = (byte) 0xD6;
    /**
     * @Fields EMPTY_BYTE : 16进制空字符(用于补位不足16位的数据)
     */
    private static final byte EMPTY_BYTE = (byte) 0x00;
    /**
     * @Fields START_PAGE : 开始页数(nfc卡前三页为基础信息,不许修改)
     */
    private static final byte START_PAGE = (byte) 0x04;
    /**
     * @Fields DATA_LENGTH : 数据长度(16字节)
     */
    private static final byte DATA_LENGTH = (byte) 0x10;
    /**
     * @Fields PAGE_SIZE : 业务数据存储页数(一页4字节,16字节需要4页)
     */
    private static final byte PAGE_SIZE = (byte) 0x04;
    /**
     * @Fields SUCCESS_CODE : 操作成功sw1
     */
    private static final String SUCCESS_CODE = "90";

    /**
     * @Title: selectCardTerminal
     * @Description: 初始化读卡器
     * @return 读卡器对象
     */
    private static CardTerminal selectCardTerminal() {
        try {
            TerminalFactory factory = TerminalFactory.getDefault();
            // 获取当前连接的所有读卡器设备
            List<CardTerminal> terminals = factory.terminals().list();
            CardTerminal terminal = null;
            // 获取第1个读卡器设备
            if (terminals.size() == 1) {
                terminal = terminals.get(0);
                return terminal;
            }

        } catch (Exception e) {
            LOGGER.error("读卡器连接失败：{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * @Title: establishConnection
     * @Description: 获取卡片信息
     * @param ct
     *            读卡器对象
     * @return 卡片对象
     */
    private static Card establishConnection(CardTerminal ct) {
        // 卡片类型为自动
        String cardType = "*";

        Card card = null;
        try {
            // 从读卡器中获取卡片对象
            card = ct.connect(cardType);
        } catch (CardException e) {
            LOGGER.error("读取卡片信息失败：{}", e.getMessage(), e);
            return null;
        }
        return card;

    }

    /**
     * @Title: read
     * @Description: 读取卡片业务数据
     * @return 读取到的数据
     */
    public static String read() {
        CardTerminal cardTerminal = selectCardTerminal();
        Card card = null;
        if (cardTerminal != null) {
            // 获取卡片对象
            card = establishConnection(cardTerminal);
            if (card != null) {
                // 获取卡片通道
                CardChannel cc = card.getBasicChannel();
                try {
                    // 发送APDU指令,读取卡片中的信息,从START_PAGE开始读取DATA_LENGTH个长度的数据
                    ResponseAPDU answer = cc.transmit(
                            new CommandAPDU(new byte[]{CLS_NFC, INS_READ, EMPTY_BYTE, START_PAGE, DATA_LENGTH}));
                    // 获取读取结果的字节数组
                    byte[] reponseBytesArr = answer.getBytes();
                    // 将读取结果转换为字符串(截取读取长度,并去除空格)
                    return new String(reponseBytesArr).substring(0, DATA_LENGTH).trim();
                } catch (CardException e) {
                    LOGGER.error("业务数据读取失败：{}", e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * @Title: write
     * @Description: 写入卡片数据
     * @param data
     *            要写入的数据（需控制在16字节以内）
     * @return 写入结果 true：成功，false：失败
     */
    public static boolean write(String data) {
        // 将要写入的数据转换为字节数组
        byte[] bytes = data.getBytes();
        // 目标字节数组
        byte[] dataByte = new byte[DATA_LENGTH];
        // 如果数组长度小于数据长度
        if (bytes.length < DATA_LENGTH) {
            // 将数组复制给目标字节数组,并使用空字节补位至DATA_LENGTH
            for (int i = 0; i < dataByte.length; i++) {
                if (i < bytes.length) {
                    dataByte[i] = bytes[i];
                } else {
                    dataByte[i] = EMPTY_BYTE;
                }
            }
            // 如果数据长度大于DATA_LENGTH,不允许写入,返回写入失败
        } else if (bytes.length > DATA_LENGTH) {
            return false;
        } else {
            // 如果长度一致,则将数组复制给目标数组
            dataByte = bytes;
        }

        CardTerminal cardTernimal = selectCardTerminal();
        Card card = null;
        if (cardTernimal != null) {
            // 获取卡片对象
            card = establishConnection(cardTernimal);
            if (card == null) {
                return false;
            }
            CardChannel cc = card.getBasicChannel();

            // 创建APDU指令数组
            byte[] bytesapdu = new byte[5 + PAGE_SIZE];
            // CLS,固定值
            bytesapdu[0] = CLS_NFC;
            // INS 写入
            bytesapdu[1] = INS_WRITE;
            // 空值
            bytesapdu[2] = EMPTY_BYTE;
            // 写入数据长度
            bytesapdu[4] = PAGE_SIZE;

            try {
                // 循环四页
                for (int currentPage = 0; currentPage < DATA_LENGTH / PAGE_SIZE; currentPage++) {
                    // APDU指令,写入第几页
                    bytesapdu[3] = (byte) (currentPage + START_PAGE);
                    for (int j = 5; j < 5 + PAGE_SIZE; j++) {
                        // data --> a b c d e f g h i j k
                        // 设置APDU后半部分的数据(4个字节)
                        bytesapdu[j] = dataByte[j + currentPage * PAGE_SIZE - 5];
                    }
                    // 写入当前页
                    ResponseAPDU answer = cc.transmit(new CommandAPDU(bytesapdu));
                    // 写入结果的sw1
                    String sw1 = Integer.toHexString(((byte) answer.getSW1() & 0xFF)).toUpperCase();
                    // 判断是否写入成功
                    if (!SUCCESS_CODE.equals(sw1)) {
                        return false;
                    }
                }
            } catch (CardException e) {
                LOGGER.error("业务数据写入失败：{}", e.getMessage(), e);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

}