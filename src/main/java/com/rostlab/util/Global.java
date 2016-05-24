package com.rostlab.util;

/**
 * Created by Longes
 */
public class Global {
	
	
	public static final char TOPO_UNKNOWN 	= '0';
	public static final char TOPO_INSIDE 	= '1';
	public static final char TOPO_OUTSIDE 	= '2';
	public static final char TOPO_MEMBRANE 	= '3';
	
	public static final char SEG_TYPE_TMH_1 	= 'H'; //transmembrane helix
	public static final char SEG_TYPE_TMH_2 	= 'h'; //transmembrane helix
	public static final char SEG_TYPE_COIL 		= 'C'; //transmembrane coil
	public static final char SEG_TYPE_BETA 		= 'B'; //transmembrane beta sheet
	public static final char SEG_TYPE_BETA_IN 	= 'I'; //inside part of a beta barrel
	public static final char SEG_TYPE_LOOP 		= 'L'; //reentrant region
	public static final char SEG_TYPE_INTERFACE = 'F'; //interfacial helices
	public static final char SEG_TYPE_NON_MEM 	= 'N'; //non-(trans)membrane region
	public static final char SEG_TYPE_UNKNOWN 	= 'U'; //everything else

}
