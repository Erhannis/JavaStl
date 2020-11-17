/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erhannis.javastl;

import com.erhannis.mathnstuff.MeMath;

/**
 *
 * @author erhannis
 */
public class Stl {
  /**
   * Convenience method for {@link #pointsToStl(double[][], java.lang.String)}
   * @param points
   * @return 
   * @see #pointsToStl(double[][], java.lang.String) 
   */
  public static String pointsToStl(double[][] points) {
    return pointsToStl(points, "pointlist");
  }
    
  /**
   * Organized like points[3N][3].
   * Points are grouped in triangles of three.
   * So like, points[0] points[1] points[2] form the first triangle,
   * points[3] points[4] points[5] form the second triangle, etc.
   * If points.length is not a multiple of 3, the stragglers are skipped.
   * Look at 3 points such that 0-1-2 moves counterclockwise.  The normal shall
   * be calculated such that it comes out of the plane towards you.
   * @param points
   * @param name
   * @return 
   */
  public static String pointsToStl(double[][] points, String name) {
    StringBuffer sb = new StringBuffer();
    sb.append("solid "+name+"\n");
    for (int i = 0; i < points.length; i += 3) {
      double[] a = points[i];
      double[] b = points[i+1];
      double[] c = points[i+2];
      
      double[] normal = MeMath.vectorNormalizeIP(MeMath.crossProduct3d(MeMath.vectorSubtract(c,b), MeMath.vectorSubtract(a,b)));
      
      sb.append("facet normal "+normal[0]+" "+normal[1]+" "+normal[2]+"\n");
      sb.append("    outer loop\n");
      sb.append("        vertex "+a[0]+" "+a[1]+" "+a[2]+"\n");
      sb.append("        vertex "+b[0]+" "+b[1]+" "+b[2]+"\n");
      sb.append("        vertex "+c[0]+" "+c[1]+" "+c[2]+"\n");
      sb.append("    endloop\n");
      sb.append("endfacet\n");
    }
    sb.append("endsolid "+name+"\n");
    
    return sb.toString();
  }
}
