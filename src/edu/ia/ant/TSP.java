package edu.ia.ant;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*--------------------------------------------------------------------*/
public class TSP {
/*--------------------------------------------------------------------*/

/* --- constants --- */
private static final int BLKSIZE = 16;

/* --- instance variables --- */
protected int        size;    /* current number of vertices */
protected int[]   xs, ys;  /* coordinates of vertices */
protected int[][] dists;   /* distances between vertices */
protected List<NodeDistance> nodeDists;
protected Map<Integer, List<NodeDistance>> nodesConnected;
protected boolean    sym;     /* flag for symmetric distances */
protected boolean    euclid;  /* flag for euclidean distances */
protected int[]      tour;    /* best (known) tour */
private   double     bbx, bby;/* bounding box of vertices */
private   double     bbw, bbh;/* (position and width and height) */
private   boolean    valid;   /* flag for valid bounding box */

/*------------------------------------------------------------------*/

public TSP () 
{                             /* --- create a traveling salesman p. */
  
  this.xs     = new int[] {10, 100, 210, 320, 430, 540, 650, 760, 870, 980,
		  					  10, 100, 210, 320, 430, 540, 650, 760, 870, 980,
		  					  10, 100, 191, 301, 411, 521, 631, 741, 851, 961,
		  					  10, 100, 0, 320, 430, 540, 0, 760, 870, 980,
		  					  10, 100, 0, 320, 430, 540, 650, 760, 870, 980,
		  					  10, 100, 210, 320, 430, 540, 650, 760, 870, 980,
		  					  10, 100, 210, 320, 430, 540, 650, 760, 870, 980};
  
  this.size   = this.xs.length;            /* initialize the variables */
  this.ys     = new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
		  					  102, 102, 102, 102, 102, 102, 102, 102, 102, 102,
		  					  212, 212, 212, 212, 212, 212, 212, 212, 212, 212,
		  					  322, 322, 0, 322, 322, 322, 0, 322, 322, 322,
		  					  432, 432, 0, 432, 432, 432, 432, 432, 432, 432,
		  					  542, 542, 542, 542, 542, 542, 542, 542, 542, 542,
		  					  652, 652, 652, 652, 652, 652, 652, 652, 652, 652};
  this.nodeDists = new ArrayList<NodeDistance>();
  nodeDists.add(new NodeDistance(0,1,90));
  nodeDists.add(new NodeDistance(0,10,102));
  nodeDists.add(new NodeDistance(1,2,90));
  nodeDists.add(new NodeDistance(1,11,102));
  nodeDists.add(new NodeDistance(2,3,90));
  nodeDists.add(new NodeDistance(2,12,102));
  nodeDists.add(new NodeDistance(3,4,90));
  nodeDists.add(new NodeDistance(3,13,102));
  nodeDists.add(new NodeDistance(4,5,90));
  nodeDists.add(new NodeDistance(4,14,102));
  nodeDists.add(new NodeDistance(5,6,90));
  nodeDists.add(new NodeDistance(5,15,102));
  nodeDists.add(new NodeDistance(6,7,90));
  nodeDists.add(new NodeDistance(6,16,102));
  nodeDists.add(new NodeDistance(7,8,90));
  nodeDists.add(new NodeDistance(7,17,102));
  nodeDists.add(new NodeDistance(8,9,90));
  nodeDists.add(new NodeDistance(8,18,102));
  nodeDists.add(new NodeDistance(9,19,102));
  nodeDists.add(new NodeDistance(10,11,90));
  nodeDists.add(new NodeDistance(10,20,102));
  nodeDists.add(new NodeDistance(11,12,90));
  nodeDists.add(new NodeDistance(11,21,102));
  nodeDists.add(new NodeDistance(12,13,90));
  nodeDists.add(new NodeDistance(12,22,102));
  nodeDists.add(new NodeDistance(13,14,90));
  nodeDists.add(new NodeDistance(13,23,102));
  nodeDists.add(new NodeDistance(14,15,90));
  nodeDists.add(new NodeDistance(14,24,102));
  nodeDists.add(new NodeDistance(15,16,90));
  nodeDists.add(new NodeDistance(15,25,102));
  nodeDists.add(new NodeDistance(16,17,90));
  nodeDists.add(new NodeDistance(16,26,102));
  nodeDists.add(new NodeDistance(17,18,90));
  nodeDists.add(new NodeDistance(17,27,102));
  nodeDists.add(new NodeDistance(18,19,90));
  nodeDists.add(new NodeDistance(18,28,102));
  nodeDists.add(new NodeDistance(19,29,102));
  nodeDists.add(new NodeDistance(20,21,90));
  nodeDists.add(new NodeDistance(20,30,102));
  nodeDists.add(new NodeDistance(21,22,90));
  nodeDists.add(new NodeDistance(21,31,102));
  nodeDists.add(new NodeDistance(22,23,90));
  nodeDists.add(new NodeDistance(23,24,90));
  nodeDists.add(new NodeDistance(23,32,102));
  nodeDists.add(new NodeDistance(24,25,90));
  nodeDists.add(new NodeDistance(24,33,102));
  nodeDists.add(new NodeDistance(25,26,90));
  nodeDists.add(new NodeDistance(25,34,102));
  nodeDists.add(new NodeDistance(26,27,90));
  nodeDists.add(new NodeDistance(27,28,90));
  nodeDists.add(new NodeDistance(27,35,102));
  nodeDists.add(new NodeDistance(28,29,90));
  nodeDists.add(new NodeDistance(28,36,102));
  nodeDists.add(new NodeDistance(29,37,102));
  nodeDists.add(new NodeDistance(30,31,90));
  nodeDists.add(new NodeDistance(30,38,102));
  nodeDists.add(new NodeDistance(31,32,220));
  nodeDists.add(new NodeDistance(32,33,90));
  nodeDists.add(new NodeDistance(32,40,102));
  nodeDists.add(new NodeDistance(33,34,90));
  nodeDists.add(new NodeDistance(33,41,102));
  nodeDists.add(new NodeDistance(34,42,220));
  nodeDists.add(new NodeDistance(35,36,90));
  nodeDists.add(new NodeDistance(35,44,102));
  nodeDists.add(new NodeDistance(36,37,90));
  nodeDists.add(new NodeDistance(36,45,102));
  nodeDists.add(new NodeDistance(37,46,90));
  nodeDists.add(new NodeDistance(38,39,90));
  nodeDists.add(new NodeDistance(39,40,220));
  nodeDists.add(new NodeDistance(39,48,90));
  nodeDists.add(new NodeDistance(40,41,90));
  nodeDists.add(new NodeDistance(40,50,102));
  nodeDists.add(new NodeDistance(41,42,90));
  nodeDists.add(new NodeDistance(41,51,90));
  nodeDists.add(new NodeDistance(42,43,90));
  nodeDists.add(new NodeDistance(42,52,102));
  nodeDists.add(new NodeDistance(43,44,90));
  nodeDists.add(new NodeDistance(43,53,102));
  nodeDists.add(new NodeDistance(44,45,90));
  nodeDists.add(new NodeDistance(44,54,102));
  nodeDists.add(new NodeDistance(45,46,90));
  nodeDists.add(new NodeDistance(45,55,102));
  nodeDists.add(new NodeDistance(46,56,102));
  nodeDists.add(new NodeDistance(47,48,90));
  nodeDists.add(new NodeDistance(47,57,102));
  nodeDists.add(new NodeDistance(48,49,90));
  nodeDists.add(new NodeDistance(48,58,102));
  nodeDists.add(new NodeDistance(49,50,90));
  nodeDists.add(new NodeDistance(49,59,102));
  nodeDists.add(new NodeDistance(50,51,90));
  nodeDists.add(new NodeDistance(50,60,102));
  nodeDists.add(new NodeDistance(51,61,102));
  nodeDists.add(new NodeDistance(52,53,90));
  nodeDists.add(new NodeDistance(52,62,102));
  nodeDists.add(new NodeDistance(53,54,90));
  nodeDists.add(new NodeDistance(53,63,102));
  nodeDists.add(new NodeDistance(54,66,90));
  nodeDists.add(new NodeDistance(54,64,102));
  nodeDists.add(new NodeDistance(55,56,90));
  nodeDists.add(new NodeDistance(55,65,102));
  nodeDists.add(new NodeDistance(56,66,102));
  nodeDists.add(new NodeDistance(57,58,90));
  nodeDists.add(new NodeDistance(58,59,90));
  nodeDists.add(new NodeDistance(60,61,90));
  nodeDists.add(new NodeDistance(61,62,90));
  nodeDists.add(new NodeDistance(62,63,90));
  nodeDists.add(new NodeDistance(63,64,90));
  nodeDists.add(new NodeDistance(64,65,90));
  nodeDists.add(new NodeDistance(65,66,90));
  
  this.nodesConnected = new HashMap<Integer, List<NodeDistance>>();
  List<NodeDistance> tmp = new ArrayList<NodeDistance>();
  tmp.add(new NodeDistance(0,1,90));
  tmp.add(new NodeDistance(0,10,102));
  nodesConnected.put(0, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(1,2,90));
  tmp.add(new NodeDistance(1,11,102));
  nodesConnected.put(1, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(2,3,90));
  tmp.add(new NodeDistance(2,12,102));
  nodesConnected.put(2, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(3,4,90));
  tmp.add(new NodeDistance(3,13,102));
  nodesConnected.put(3, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(4,5,90));
  tmp.add(new NodeDistance(4,14,102));
  nodesConnected.put(4, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(5,6,90));
  tmp.add(new NodeDistance(5,15,102));
  nodesConnected.put(5, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(6,7,90));
  tmp.add(new NodeDistance(6,16,102));
  nodesConnected.put(6, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(7,8,90));
  tmp.add(new NodeDistance(7,17,102));
  nodesConnected.put(7, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(8,9,90));
  tmp.add(new NodeDistance(8,18,102));
  nodesConnected.put(8, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(9,19,102));
  nodesConnected.put(9, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(10,11,90));
  tmp.add(new NodeDistance(10,20,102));
  nodesConnected.put(10, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(11,12,90));
  tmp.add(new NodeDistance(11,21,102));
  nodesConnected.put(11, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(12,13,90));
  tmp.add(new NodeDistance(12,22,102));
  nodesConnected.put(12, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(13,14,90));
  tmp.add(new NodeDistance(13,23,102));
  nodesConnected.put(13, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(14,15,90));
  tmp.add(new NodeDistance(14,24,102));
  nodesConnected.put(14, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(15,16,90));
  tmp.add(new NodeDistance(15,25,102));
  nodesConnected.put(15, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(16,17,90));
  tmp.add(new NodeDistance(16,26,102));
  nodesConnected.put(16, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(17,18,90));
  tmp.add(new NodeDistance(17,27,102));
  nodesConnected.put(17, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(18,19,90));
  tmp.add(new NodeDistance(18,28,102));
  nodesConnected.put(18, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(19,29,102));
  nodesConnected.put(19, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(20,21,90));
  tmp.add(new NodeDistance(20,30,102));
  nodesConnected.put(20, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(21,22,90));
  tmp.add(new NodeDistance(21,31,102));
  nodesConnected.put(21, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(22,23,90));
  nodesConnected.put(22, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(23,24,90));
  tmp.add(new NodeDistance(23,32,102));
  nodesConnected.put(23, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(24,25,90));
  tmp.add(new NodeDistance(24,33,102));
  nodesConnected.put(24, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(25,26,90));
  tmp.add(new NodeDistance(25,34,102));
  nodesConnected.put(25, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(26,27,90));
  nodesConnected.put(26, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(27,28,90));
  tmp.add(new NodeDistance(27,35,102));
  nodesConnected.put(27, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(28,29,90));
  tmp.add(new NodeDistance(28,36,102));
  nodesConnected.put(28, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(29,37,102));
  nodesConnected.put(29, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(30,31,90));
  tmp.add(new NodeDistance(30,38,102));
  nodesConnected.put(30, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(31,32,220));
  nodesConnected.put(31, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(32,33,90));
  tmp.add(new NodeDistance(32,40,102));
  nodesConnected.put(32, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(33,34,90));
  tmp.add(new NodeDistance(33,41,102));
  nodesConnected.put(33, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(34,42,220));
  nodesConnected.put(34, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(35,36,90));
  tmp.add(new NodeDistance(35,44,102));
  nodesConnected.put(35, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(36,37,90));
  tmp.add(new NodeDistance(36,45,102));
  nodesConnected.put(36, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(37,46,90));
  nodesConnected.put(37, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(38,39,90));
  nodesConnected.put(38, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(39,40,220));
  tmp.add(new NodeDistance(39,48,90));
  nodesConnected.put(39, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(40,41,90));
  tmp.add(new NodeDistance(40,50,102));
  nodesConnected.put(40, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(41,42,90));
  tmp.add(new NodeDistance(41,51,90));
  nodesConnected.put(41, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(42,43,90));
  tmp.add(new NodeDistance(42,52,102));
  nodesConnected.put(42, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(43,44,90));
  tmp.add(new NodeDistance(43,53,102));
  nodesConnected.put(43, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(44,45,90));
  tmp.add(new NodeDistance(44,54,102));
  nodesConnected.put(44, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(45,46,90));
  tmp.add(new NodeDistance(45,55,102));
  nodesConnected.put(45, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(46,56,102));
  nodesConnected.put(46, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(47,48,90));
  tmp.add(new NodeDistance(47,57,102));
  nodesConnected.put(47, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(48,49,90));
  tmp.add(new NodeDistance(48,58,102));
  nodesConnected.put(48, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(49,50,90));
  tmp.add(new NodeDistance(49,59,102));
  nodesConnected.put(49, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(50,51,90));
  tmp.add(new NodeDistance(50,60,102));
  nodesConnected.put(50, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(51,61,102));
  nodesConnected.put(51, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(52,53,90));
  tmp.add(new NodeDistance(52,62,102));
  nodesConnected.put(52, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(53,54,90));
  tmp.add(new NodeDistance(53,63,102));
  nodesConnected.put(53, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(54,66,90));
  tmp.add(new NodeDistance(54,64,102));
  nodesConnected.put(54, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(55,56,90));
  tmp.add(new NodeDistance(55,65,102));
  nodesConnected.put(55, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(56,66,102));
  nodesConnected.put(56, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(57,58,90));
  nodesConnected.put(57, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(58,59,90));
  nodesConnected.put(58, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(60,61,90));
  nodesConnected.put(60, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(61,62,90));
  nodesConnected.put(61, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(62,63,90));
  nodesConnected.put(62, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(63,64,90));
  nodesConnected.put(63, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(64,65,90));
  nodesConnected.put(64, tmp);
  tmp.clear();
  tmp.add(new NodeDistance(65,66,90));
  nodesConnected.put(65, tmp);
  tmp.clear();
  
  this.euclid = true;         /* default: Euclidean distances */
  this.sym    = true;         /* default: symmetric distances */
  this.tour   = null;         /* there is no known (best) tour */
  this.valid  = false;        /* the bounding box is not valid */
  
}  /* TSP() */

/*------------------------------------------------------------------*/

public int    size   ()      { return this.size; }
public double getX   (int i) { return this.xs[i]; }
public double getY   (int i) { return this.ys[i]; }
public void   setPos (int i, int x, int y)
{ this.xs[i] = x; this.ys[i] = y; }

/*------------------------------------------------------------------*/

private void bbox ()
{                             /* --- compute bounding box */
  int    i;                   /* loop variable */
  double x, y;                /* coordinates of a vertex */
  double xmax, ymax;          /* maximal x- and y-coordinates */

  this.bbx = Double.MAX_VALUE; xmax = -Double.MAX_VALUE;
  this.bby = Double.MAX_VALUE; ymax = -Double.MAX_VALUE;
  for (i = this.xs.length; --i >= 0; ) {
    x = this.xs[i];          /* traverse the vertices */
    y = this.ys[i];          /* of the problem */
    if (x < this.bbx) this.bbx = x;
    if (x > xmax)     xmax     = x;
    if (y < this.bby) this.bby = y;
    if (y > ymax)     ymax     = y;
  }                           /* find minimum and maximum coords. */
  this.bbw = xmax -this.bbx;  /* compute the width and height */
  this.bbh = ymax -this.bby;  /* of the bounding box */
  this.valid = true;          /* the bounding box is now valid */
}  /* bbox() */

/*------------------------------------------------------------------*/

public double getX ()
{ if (!this.valid) this.bbox(); return this.bbx; }

public double getY ()
{ if (!this.valid) this.bbox(); return this.bby; }

public double getWidth ()
{ if (!this.valid) this.bbox(); return this.bbw; }

public double getHeight ()
{ if (!this.valid) this.bbox(); return this.bbh; }


/*------------------------------------------------------------------*/

public boolean isSymmetric ()
{ return this.sym; }

public double  getDist     (int i, int j)
{ return this.dists[i][j]; }

/*------------------------------------------------------------------*/

public int[] getTour () { return this.tour; }

public void  setTour (int[] tour)
{                             /* --- set a (best) tour */
  if (this.tour == null)      /* create a tour if necessary */
    this.tour = new int[this.size];
  System.arraycopy(tour, 0, this.tour, 0, this.size);
}  /* setTour() */            /* copy the given tour */

/*------------------------------------------------------------------*/

public String toString ()
{                             /* --- create a string description */
  int          i, k;          /* loop variables */
  StringBuffer s;             /* created string description */

  s = new StringBuffer("TSP = {\n");
  s.append("  vertices = {"); /* there are always vertices */
  s.append("\n    (" +this.xs[0] +", " +this.ys[0] +")");
  for (i = 1; i < this.size; i++)
    s.append(",\n    (" +this.xs[i] +", " +this.ys[i] +")");
  s.append("\n  };\n");       /* list the vertices */
  if (!this.euclid) {         /* if the distances are not Euclidean */
    s.append("  distances = {");
    for (i = 0; i < this.size; i++) {
      if (i > 0) s.append(",");
      s.append("\n    { " +this.dists[i][0]);
      for (k = 1; k < this.size; k++)
        s.append(", " +this.dists[i][k]);
      s.append(" }");         /* list the pairwise distances */
    }                         /* (which are possibly asymmetric, */
    s.append("\n  };\n");     /* so that the full distance matrix */
  }                           /* may be needed) */
  if (this.tour != null) {    /* if a (best) tour is known */
    s.append("  tour = {\n    " +this.tour[0]);
    for (i = 1; i < this.size; i++)
      s.append(", " +this.tour[i]);
    s.append("\n  };\n");     /* list the vertices of the tour */
  }
  s.append("};\n");           /* terminate the description */
  return s.toString();        /* return the created string */
}  /* toString() */



/*------------------------------------------------------------------*/

public static void main (String args[])
{                             /* --- main function for testing */
  int    size;                /* number of vertices */
  double scale = 1.0;         /* scaling factor */
  long   seed;                /* seed value for random numbers */
  TSP    tsp;                 /* created random TSP */

  tsp = new TSP();
  //tsp.transform(scale,0,0);   /* create a TSP and scale it */
  //System.out.print(tsp);      /* print the created TSP */
}  /* main() */

}  /* class TSP */
