package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends FlatGeometry  {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point> _vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected Plane _plane;
	private int size;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this._vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		_plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = _plane.getNormal(null);

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (var i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
		size = vertices.length;
		//this.normal = n;
		setBoundingBox();
	}

	@Override
	public Vector getNormal(Point point) {
		//return this.normal;
		return _plane.getNormal(null);
	}


	/**
	 * we've learned the calculations from the introduction lecture
	 * @param ray ray toward the object
	 * @return the list of intersection points
	 */
	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistanc ,boolean bb) {

		List<GeoPoint> result = _plane.findGeoIntersections(ray);

		if (result == null) {
			return result;
		}
//
		Point P0 = ray.getP0();
		Vector v = ray.getDir();//

		Point P1 = _vertices.get(1);
		Point P2 = _vertices.get(0);

		Vector v1 = P1.subtract(P0);
		Vector v2 = P2.subtract(P0);

		double sign = alignZero(v.dotProduct(v1.crossProduct(v2)));

		if (isZero(sign)) {
			return null;
		}

		boolean positive = sign > 0;

		//iterate through all vertices of the polygon
		for (int i = _vertices.size() - 1; i > 0; --i) {
			v1 = v2;
			v2 = _vertices.get(i).subtract(P0);

			sign = alignZero(v.dotProduct(v1.crossProduct(v2)));
			if (isZero(sign)) {
				return null;
			}

			if (positive != (sign > 0)) {
				return null;
			}
		}

		return List.of(new GeoPoint(this,result.get(0).point));


	}


	/**
	 * method sets the values of the bounding volume for the intersectable polygon
	 */
	@Override
	public void setBoundingBox() {
		super.setBoundingBox();

		double minX = Double.POSITIVE_INFINITY;
		double minY = Double.POSITIVE_INFINITY;
		double minZ = Double.POSITIVE_INFINITY;

		double maxX = Double.NEGATIVE_INFINITY;
		double maxY = Double.NEGATIVE_INFINITY;
		double maxZ = Double.NEGATIVE_INFINITY;

		for (Point vertex : _vertices) {
			// get minimal & maximal x value for the containing box
			minX = Math.min(vertex.get_x(), minX);
			maxX = Math.max(vertex.get_x(), maxX);

			// get minimal & maximal y value for the containing box
			minY = Math.min(vertex.get_y(), minY);
			maxY = Math.max(vertex.get_y(), maxY);

			// get minimal & maximal z value for the containing box
			minZ = Math.min(vertex.get_z(), minZ);
			maxZ = Math.max(vertex.get_z(), maxZ);
		}

		// set the minimum and maximum values in 3 axes for this bounding region of the component
		boundingBox.setBoundingBox(minX, maxX, minY, maxY, minZ, maxZ);
	}



}
