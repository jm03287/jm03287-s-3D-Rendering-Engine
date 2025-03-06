import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.ArrayList;

public class GraphicsEngine extends JPanel{

	private float cameraX, cameraY, cameraZ;
	private float cubeLocX, cubeLocY, cubeLocZ;
	private int cubeSize;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		Dimension size = getSize();
		Insets insets = getInsets();
		int width = size.width - insets.right - insets.left;
		int height = size.height - insets.bottom - insets.top;
		cameraX = 0.0f; cameraY = -1.1f; cameraZ = 1.01f;
		cubeLocX = 0.0f; cubeLocY = 0.0f; cubeLocZ = 0.0f;
		float aspect = width / height;
		int cubeSize = 100;
		
		TransformationMatrix4x4f mMat = new TransformationMatrix4x4f();
		mMat.scaleCoords(cubeSize, cubeSize, cubeSize);
		mMat.translateCoords(cubeLocX, cubeLocY, cubeLocZ);
		
		TransformationMatrix4x4f pMat = TransformationMatrix4x4f.projectionMatrix4x4(90.0f, aspect, 0.1f, 10000.0f);

		TransformationMatrix4x4f mvMat = mMat.modelViewCoordsMatrixs4x4(cameraX, cameraY, cameraZ, 0.0f, 0.0f, 0.0f);
		TransformationMatrix4x4f mvpMat = pMat.transformMatrix4x4(mvMat);
		
		float[][] vertex_positions = {{-1f, -1f, -1f, 1f},{-1f, -1f, 1f, 1f},{-1f, 1f, -1f, 1f},
									  {-1f, 1f, 1f, 1f},{1f, -1f, -1f, 1f},{1f, -1f, 1f, 1f},
									  {1f, 1f, -1f, 1f},{1f, 1f, -1f, 1f}};
		
		ArrayList<hVector3D> cubeData = initModel(vertex_positions);
		cubeData = transformModel(cubeData, mvpMat);
		for(int i = 0; i < cubeData.size()-1; i++) {
			int XCoord1 = (int)(cubeData.get(i).getXCOORD()/cubeData.get(i).getHWCOORD()+(width/2));
			int YCoord1 = (int)(cubeData.get(i).getYCOORD()/cubeData.get(i).getHWCOORD()+(height/2));
			int XCoord2 = (int)(cubeData.get(i+1).getXCOORD()/cubeData.get(i+1).getHWCOORD()+(width/2));
			int YCoord2 = (int)(cubeData.get(i+1).getYCOORD()/cubeData.get(i+1).getHWCOORD()+(height/2));
			g2d.drawLine(XCoord1, YCoord1, XCoord2, YCoord2);
		}
	}
	
	public ArrayList<hVector3D> initModel(float[][] coords) {
		ArrayList<hVector3D> vecArr = new ArrayList<hVector3D>();
		for(int i = 0; i < coords.length; i++) {
			vecArr.add(new hVector3D(new float[]{coords[i][0],coords[i][1],coords[i][2],coords[i][3]}));
		}
		return vecArr;
	}
	
	public ArrayList<hVector3D> transformModel(ArrayList<hVector3D> modelData, TransformationMatrix4x4f tMat) {
		ArrayList<hVector3D> vecArr = new ArrayList<hVector3D>();
		for(int i = 0; i < modelData.size(); i++) {
			vecArr.add(TransformationMatrix4x4f.transformHVector3D(tMat, modelData.get(i)));
		}
		return vecArr;
	}
	
	public GraphicsEngine() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphicsEngine graphicsEngine = new GraphicsEngine();
		JFrame frame = new JFrame("Sample");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(graphicsEngine);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
