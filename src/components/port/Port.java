package components.port;

import java.awt.*;
import java.util.ArrayList;

public class Port {
    private Point northPort;
    private Point eastPort;
    private Point southPort;
    private Point westPort;

    public Port(Point northPort, Point eastPort, Point southPort, Point westPort) {
        this.northPort = northPort;
        this.eastPort = eastPort;
        this.southPort = southPort;
        this.westPort = westPort;
    }

    public Point getNorthPort() {
        return this.northPort;
    }

    public Point getEastPort() {
        return this.eastPort;
    }

    public Point getSouthPort() {
        return this.southPort;
    }

    public Point getWestPort() {
        return this.westPort;
    }

    public ArrayList<Point> getPorts() {
        ArrayList<Point> ports = new ArrayList<Point>();

        ports.add(this.northPort);
        ports.add(this.eastPort);
        ports.add(this.southPort);
        ports.add(this.westPort);

        return ports;
    }
}
