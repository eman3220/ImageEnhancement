package main;

import javax.swing.JOptionPane;

public class ImageEnhancement {
	public static void main(String[] args){
		if(args.length!=1){
			JOptionPane.showMessageDialog(null, "One argument please");
			return;
		}
		if(!args[0].endsWith(".png")){
			JOptionPane.showMessageDialog(null, "PNG file please");
			return;
		}

		new ImageEnhancement(args[0]);
	}

	/**
	 *
	 * @param imagepath
	 */
	public ImageEnhancement(String imagepath){
		int[][] input = ImageHandler.readImage(imagepath);
		ImageHandler.displayImage("original", input);

		// create enhancement filter
		double[][] laplacianMask1 = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
		double[][] laplacianMask2 = { { 1, -2, 1 }, { -2, 5, -2 }, { 1, -2, 1 } };

		// apply enhancement filter
		int[][] output = ImageHandler.applyConvolutionMask(input, laplacianMask1);
		output = ImageHandler.brightenImage(output);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageHandler.displayImage("new image", output);
	}
}
