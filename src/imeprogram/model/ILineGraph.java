package imeprogram.model;

/**
 * This interface represents a tool used to draw line graphs.
 */
public interface ILineGraph {

  /**
   * Draws a line graph using given data and returns an IImage of the drawing.
   *
   * @param data   the data to be plotted. Here, Index0 -> Points on X axis and Index1 -> Points to
   *               plot on Y axis.
   * @param height the height of the plot.
   * @param width  the width of the plot.
   * @return an IImage of the line graph.
   */
  IImage drawLineGraph(int[][] data, int height, int width);
}
