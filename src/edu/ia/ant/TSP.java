package edu.ia.ant;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.Reader;
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
protected boolean    sym;     /* flag for symmetric distances */
protected boolean    euclid;  /* flag for euclidean distances */
protected int[]      tour;    /* best (known) tour */
private   double     bbx, bby;/* bounding box of vertices */
private   double     bbw, bbh;/* (position and width and height) */
private   boolean    valid;   /* flag for valid bounding box */

/*------------------------------------------------------------------*/

public TSP () { this(BLKSIZE); }

public TSP (int size)
{                             /* --- create a traveling salesman p. */
  this.size   = 0;            /* initialize the variables */
  this.xs     = new int[] {10, 100, 210, 320, 430, 540, 650, 760, 870, 980,
		  					  10, 100, 210, 320, 430, 540, 650, 760, 870, 980,
		  					  10, 100, 191, 301, 411, 521, 631, 741, 851, 961,
		  					  10, 100, 320, 430, 540, 760, 870, 980,
		  					  10, 100, 320, 430, 540, 650, 760, 870, 980,
		  					  10, 100, 210, 320, 430, 540, 650, 760, 870, 980,
		  					  10, 100, 210, 320, 430, 540, 650, 760, 870, 980};
  this.ys     = new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10,
		  					  102, 102, 102, 102, 102, 102, 102, 102, 102, 102,
		  					  212, 212, 212, 212, 212, 212, 212, 212, 212, 212,
		  					  322, 322, 322, 322, 322, 322, 322, 322,
		  					  432, 432, 432, 432, 432, 432, 432, 432, 432,
		  					  542, 542, 542, 542, 542, 542, 542, 542, 542, 542,
		  					  652, 652, 652, 652, 652, 652, 652, 652, 652, 652};
  this.dists  = new int [][] {{0, 70, 0, 0, 0, 0, 0, 0, 0, 0,
	  						  75, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {70, 0, 70, 0, 0, 0, 0, 0, 0, 0,
 	  						  0, 75, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0},
 	  						  {0, 70, 0, 70, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 75, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0},
 	  						  {0, 0, 70, 0, 70, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 75, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0},
  	  						  {0, 0, 0, 70, 0, 70, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 75, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0},
   	  						  {0, 0, 0, 0, 70, 0, 70, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 75, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0},
   	  						  {0, 0, 0, 0, 0, 70, 0, 70, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 75, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
 	  						   0, 0, 0, 0, 0, 0},
 	  						  {0, 0, 0, 0, 0, 0, 70, 0, 70, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 75, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 70, 0, 70,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 75, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 70, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 75,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {75, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 70, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 75, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   70, 0, 70, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 75, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 70, 0, 70, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 75, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 70, 0, 70, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 75, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 70, 0, 70, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 75, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 70, 0, 70, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 75, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 70, 0, 70, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 75, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 70, 0, 70, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 75, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 70, 0, 70,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 75,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 70, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   75, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 70, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 75, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   70, 0, 70, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 75, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 70, 0, 70, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 75, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 70, 0, 70, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 75, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 70, 0, 70, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 75, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 70, 0, 70, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 75, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 70, 0, 70, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 75, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 70, 0, 70, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 75, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 70, 0, 70,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
	  						  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 75,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 70, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	  						   0, 0, 0, 0, 0, 0},
  								};
  
  this.euclid = true;         /* default: Euclidean distances */
  this.sym    = true;         /* default: symmetric distances */
  this.tour   = null;         /* there is no known (best) tour */
  this.valid  = false;        /* the bounding box is not valid */
  
}  /* TSP() */

/*------------------------------------------------------------------*/

public TSP (int size, Random rand)
{                             /* --- create a traveling salesman p. */
  this(size);                 /* initialize the variables, */
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

  tsp = new TSP(66);
  //tsp.transform(scale,0,0);   /* create a TSP and scale it */
  //System.out.print(tsp);      /* print the created TSP */
}  /* main() */

}  /* class TSP */