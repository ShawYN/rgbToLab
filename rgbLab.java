import java.util.Scanner;

public class rgbLab{
	double[][] M = new double[][] {{0.436052025,0.385081593,0.143087414},{0.222491598,0.716886060,0.060621486},{0.013929122,0.097097002,0.714185470}};

	public void init(){}

	public double gamma(double x){
		double res;
		if(x > 0.04045){
			res = Math.pow((x + 0.055) / 1.055,2.4);
		}
		else{
			res = x / 12.92;
		}
		return res;
	}

	public double func(double t){
		double res;
		if(t > Math.pow(6/29,3)){
			res = Math.pow(t,1/3);
		}
		else{
			res = t * Math.pow(29/6,2) / 3 + 4/29;
		}
		return res;
	}

	public double[] rgbToRGB(double rgb[]){
		double[] RGB = new double [3];
		for(int i = 0; i < 3; i++){
			RGB[i] = gamma(rgb[i] / 255.0);
		}
		return RGB;
	}

	public double[] RGBToXYZ(double RGB[]){
		double[] XYZ = new double [3];
		for(int i = 0;i < 3; i++){
			for(int j = 0;j < 3; j++){
				XYZ[i] += 100 * M[i][j] * RGB[i];
			}
		}
		return XYZ;
	}

	public double[] XYZToLab(double XYZ[]){
		double[] Lab = new double[3];
		Lab[0] = 116 * func(XYZ[1]/100) - 16;
		Lab[1] = 500 * (func(XYZ[0]/95.047) - func(XYZ[1]/100));
		Lab[2] = 200 * (func(XYZ[1]/100) - func(XYZ[2]/108.883));
		return Lab;
	}

	public void PrintArray(double M[]){
		for(int i = 0; i < M.length; i++){
			System.out.print(M[i] + "\t");
		}
		System.out.println("\n");
	}

	public static void main(String[] args){
		rgbLab n = new rgbLab();
		Scanner scan = new Scanner(System.in);
		double[] rgb = new double [3];
		double[] RGB = new double [3];
		double[] XYZ = new double [3];
		double[] Lab = new double [3];
		System.out.println("Input r, g, b one by one:");
		for(int i = 0;i < 3;i++){
			rgb[i] = scan.nextDouble();
		}
		RGB = n.rgbToRGB(rgb);
		XYZ = n.RGBToXYZ(RGB);
		Lab = n.XYZToLab(XYZ);
		System.out.println("RGB:");
		n.PrintArray(RGB);
		System.out.println("XYZ:");
		n.PrintArray(XYZ);
		System.out.println("Lab:");
		n.PrintArray(Lab);
	}
}