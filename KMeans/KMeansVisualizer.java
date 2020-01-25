import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Armin on 9/21/2017.
 */
public class KMeansVisualizer extends JPanel implements MouseListener {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("KMeans Visualizer");
        window.setSize(600,600);

        KMeansVisualizer kmv = new KMeansVisualizer();

        window.add(kmv);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public KMeansVisualizer(){
        this.setBackground(Color.BLACK);
        this.addMouseListener(this);

        JButton solveButton = new JButton("Solve !");
        solveButton.setSize(100,30);
        solveButton.setLocation(10,10);
        solveButton.addActionListener((ActionEvent e) -> {startSolving();
        });

        this.setLayout(null);
        this.add(solveButton);
    }

    //instantiate KMeans Solver
    KMeans KM = new KMeans();

    //Set colors for each cluster
    Color[] colorArr = {Color.GREEN,Color.RED,Color.BLUE,Color.YELLOW};

    private void startSolving(){
        KM.Solve(3,600,800);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        for(KMeansNode p : KM.nodes) {
            if(p.assignedCentroid == null){
                g.setColor(Color.WHITE);
            }else{
                g.setColor(colorArr[p.assignedCentroid.index]);
            }
            g.fillOval((int)p.X - 5, (int)p.Y - 5, 10, 10);
        }

        for(KMeansCentroid c : KM.centeroids){
            g.setColor(colorArr[c.index]);
            g.fillRect((int)c.X - 5, (int)c.Y - 5, 10, 10);
            g.setColor(Color.WHITE);
            g.drawRect((int)c.X - 5, (int)c.Y - 5, 10, 10);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        KM.nodes.add(new KMeansNode(e.getX(),e.getY()));
        repaint();
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
