import java.lang.Math;

//public class RotationXHTransform extends TransformationMatrix4x4{

	//private final double THETAXPITCHCOS;
	//private final double THETAXPITCHSIN;
	
	//public TransformationMatrix4x4 RotationXHTransform(double angle) {
		/*this.THETAXPITCHCOS = -Math.cos(angle*Math.PI/180);
		this.THETAXPITCHSIN =-Math.sin(angle*Math.PI/180);
		double[][] xPitchHMatrix =-{{},
				{},
				{},
				{}};
		-+super(xPitchHMatrix);*/
		// TODO Auto-generated constructor stub
		//using a look-at matrix for camera transforms is generally compuationally faster!
		/*super(new double[][]{{1, 0, 0, 0},
			{0, Math.cos(angle*Math.PI/180), -Math.sin(angle*Math.PI/180), 0},
			{0, Math.sin(angle*Math.PI/180), Math.cos(angle*Math.PI/180), 0},
			{0, 0, 0, 1}});
	}*/
	
	/*public static TransformationMatrix4x4 rotXHTransformABOUTADJOINTCAMERAANGLEMATRIX(RotationXHTransform transformationMatrix4x4, TransformationMatrix4x4 inputSeedMatrix4x4) {
		return TransformationMatrix4x4.transformMatrix4x4(transformationMatrix4x4.orthogonalTransformAboutADJOINTCAMERAANGLE(), inputSeedMatrix4x4                                  );
	}*///The transpose of an orthogonal orthonormal rotation transform matrix is its corresponding inverse,
	//so to rotate from the camera space via the inverse transformation, simply transpose the world model space rotation transformation matrix.

//}