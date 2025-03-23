import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ChartPanel extends JPanel {
    private final ArrayList<Double> data;

    public ChartPanel(ArrayList<Double> data) {
        this.data = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data.isEmpty()) return;

        int width = getWidth();
        int height = getHeight();
        int padding = 25;
        int labelPadding = 25;
        int pointWidth = 4;

        int availablePlotWidth = width - labelPadding - padding;
        int availablePlotHeight = height - padding - labelPadding;

        double minYValue = 0;
        double maxYValue = getMaxDataValue();
        double maxDataValue = getMaxDataValue();
        double minDataValue = getMinDataValue();

        if (minYValue == maxYValue) {
            maxYValue += 1.0;
            minYValue -= 1.0;
        }

        data.replaceAll(aDouble -> aDouble - minDataValue);

        double yScale = (double) availablePlotHeight / (maxYValue - minYValue);
        double xScale = (data.size() > 1) ?
                (double) availablePlotWidth / (data.size() - 1) : 0;

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            int x = (data.size() > 1) ?
                    labelPadding + (int) (i * xScale) :
                    labelPadding + availablePlotWidth / 2;
            int y = padding + (int) ((maxDataValue - data.get(i)) * yScale);
            graphPoints.add(new Point(x, y));
        }

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);

        g.drawLine(labelPadding, padding, labelPadding, height - labelPadding);
        g.drawLine(labelPadding, height - labelPadding, width - padding, height - labelPadding);

        for (int i = 0; i < graphPoints.size() - 1; i++) {
            Point p1 = graphPoints.get(i);
            Point p2 = graphPoints.get(i + 1);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        for (Point point : graphPoints) {
            g.fillOval(point.x - pointWidth / 2, point.y - pointWidth / 2, pointWidth, pointWidth);
        }
    }

    private double getMinDataValue() {
        if (data.isEmpty()) return 0;
        double minData = Double.MAX_VALUE;
        for (Double dataValue : data) {
            minData = Math.min(minData, dataValue);
        }
        return minData;
    }

    private double getMaxDataValue() {
        if (data.isEmpty()) return 0;
        double maxData = Double.NEGATIVE_INFINITY;
        for (Double dataValue : data) {
            maxData = Math.max(maxData, dataValue);
        }
        return maxData;
    }
}