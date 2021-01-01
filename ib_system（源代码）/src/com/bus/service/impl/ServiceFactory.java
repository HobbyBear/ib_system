package com.bus.service.impl;

import com.bus.service.BusService;
import com.bus.service.LineService;
import com.bus.service.RoleService;
import com.bus.service.SchedulingService;
import com.bus.service.StationService;
import com.bus.service.UserService;

public class ServiceFactory {

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static BusService getBusService() {
        return new BusServiceImpl();
    }

    public static LineService getLineService() {
        return new LineServiceImpl();
    }

    public static StationService getStationService() {
        return new StationServiceImpl();
    }

    public static SchedulingService getSchedulingService() {
        return new SchedulingServiceImpl();
    }

    public static RoleService getRoleService() {
        return new RoleServiceImpl();
    }

}
