import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Armin on 9/21/2017.
 */

public class KMeans {

    public ArrayList<KMeansNode> nodes;
    public ArrayList<KMeansCentroid> centeroids;

    public KMeans(){
        nodes = new ArrayList<>();
        centeroids = new ArrayList<>();
    }

    public void Solve(int k,int uxbound,int uybound){
        //Create Random Centroids
        Random rnd = new Random();
        centeroids = new ArrayList<>();
        for (int i = 0; i < k ; i++) {
            centeroids.add(new KMeansCentroid(rnd.nextInt(uxbound),rnd.nextInt(uybound),i));
        }

        boolean isCentersUpdated = true;

        while(isCentersUpdated) {

            //Assign Centroids to Nodes
            for (KMeansNode node : nodes) {
                double bestDistance = Double.MAX_VALUE;
                for (KMeansCentroid center : centeroids) {
                    double d = distance(center, node);
                    if (d < bestDistance) {
                        node.assignedCentroid = center;
                        center.cluster.add(node);
                        bestDistance = d;
                    }
                }
            }

            isCentersUpdated = false;
            //Calculate Centeroids New Position
            for (KMeansCentroid center : centeroids) {
                int ncount = center.cluster.size();
                if(ncount > 0) {
                    double xsum = 0;
                    double ysum = 0;
                    for (int i = 0; i < ncount; i++) {
                        xsum += center.cluster.get(i).X;
                        ysum += center.cluster.get(i).Y;
                    }
                    double nx = xsum / ncount;
                    double ny = ysum / ncount;
                    if (Math.abs(nx - center.X) > 0.05) isCentersUpdated = true;
                    if (Math.abs(ny - center.Y) > 0.05) isCentersUpdated = true;
                    center.X = nx;
                    center.Y = ny;
                }
            }

        }


    }

    private double distance(KMeansCentroid c,KMeansNode n){
        return Math.sqrt(Math.pow(c.X-n.X,2) + Math.pow(c.Y-n.Y,2));
    }

}

class KMeansCentroid {
    public double X;
    public double Y;
    public int index;
    public ArrayList<KMeansNode> cluster;

    public KMeansCentroid(double x , double y,int i){
        this.X = x;
        this.Y = y;
        index = i;
        cluster = new ArrayList<>();
    }
}

class KMeansNode {
    public double X;
    public double Y;
    public KMeansCentroid assignedCentroid;

    public KMeansNode(double x, double y){
        this.X = x;
        this.Y = y;
        assignedCentroid = null;
    }
}

