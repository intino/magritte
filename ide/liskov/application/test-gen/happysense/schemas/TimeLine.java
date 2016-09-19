package happysense.schemas;

public class TimeLine {

    private java.util.List<Point> pointList = new java.util.ArrayList<>();

    public java.util.List<Point> pointList() {
        return this.pointList;
    }

    public TimeLine pointList(java.util.List<Point> pointList) {
        this.pointList = pointList;
        return this;
    }

}