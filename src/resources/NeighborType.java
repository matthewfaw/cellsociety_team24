package resources;

/**
 * An enumeration of the types of neighbors to consider
 * The purpose is to allow us to avoid continually referencing the neighbor type by 
 * the string name 
 * 
 * One may switch on this class in the following way:
 * NeighborType aNeighborType = ...
 * switch(aNeighborType ) {
 * 	case Edges:
 * 		//do stuff
 * 		break;
 * 	case Vertices:
 * 		//do vertex stuff
 * 		break;
 * 	case StrangeTriangle: // the Triangle access pattern specified by Duvall
 * 		// do stuff
 * 		break;
 *  default:
 *  	// do stuff
 *  	break;
 * }
 * 
 * @author matthewfaw
 *
 */
public enum NeighborType {
	Edges,
	Vertices,
	StrangeTriangle// i.e. the strange access pattern for Triangles... idk what else to call it :P
}