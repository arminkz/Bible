import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ConvexHullVisualizer extends JPanel implements MouseListener{

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("ConvexHull Visualizer");
        window.setSize(600,600);

       ConvexHullVisualizer chv = new ConvexHullVisualizer();

        window.add(chv);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public ConvexHullVisualizer(){
        this.setBackground(Color.BLACK);
        this.addMouseListener(this);

        JButton solveButton = new JButton("Gift Wrapping");
        solveButton.setSize(100,30);
        solveButton.setLocation(10,10);
        solveButton.addActionListener((ActionEvent e) -> {startSolving();
        });

        this.setLayout(null);
        this.add(solveButton);
    }

    ArrayList<Point> points = new ArrayList<>();
    ArrayList<Point> hull = null;

    public void startSolving(){
        hull = JarvisConvexHull.convexHull(points);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        for(Point p : points){
            g.fillOval(p.x-5,p.y-5,10,10);
        }

        if(hull != null){
            Polygon hullPoly = new Polygon();
            for(Point p : hull){
                hullPoly.addPoint(p.x,p.y);
            }
            g.drawPolygon(hullPoly);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        points.add(new Point(e.getX(),e.getY()));
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
