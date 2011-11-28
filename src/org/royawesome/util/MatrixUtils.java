package org.royawesome.util;

import org.lwjgl.util.vector.Matrix4f;

public class MatrixUtils {
	public static Matrix4f createPerspective(float fov, float aspect, float znear, float zfar) {

		float ymax, xmax;
		float temp, temp2, temp3, temp4;
		ymax = znear * (float) Math.tan(fov * Math.PI / 360.0);
		// ymin = -ymax;
		// xmin = -ymax * aspectRatio;
		xmax = ymax * aspect;

		temp = 2.0f * znear;
		temp2 = xmax - -xmax;
		temp3 = ymax - -ymax;
		temp4 = zfar - znear;

		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		matrix.m00 = temp / temp2;
		matrix.m11 = temp / temp3;
		matrix.m20 = (xmax + -xmax) / temp2;
		matrix.m21 = (ymax + -ymax) / temp3;
		matrix.m22 = (-zfar - znear) / temp4;
		matrix.m23 = -1;
		matrix.m32 = (-temp * zfar) / temp4;

		return matrix;
	}
	
	public static Matrix4f createOrthographic(float right, float left, float top, float bottom, float near, float far){
		Matrix4f ortho = new Matrix4f();
		float tx = -((right+left) / (right-left));
		float ty = -((top + bottom) / (top - bottom));
		float tz = -((far+near) / (far - near));
		
		ortho.setIdentity();
		ortho.m00 = 2.0f / (right-left);
		ortho.m11 = 2.0f / (top-bottom);
		ortho.m22 = -2.0f/ (far-near);
		
		ortho.m30 = tx;
		ortho.m31 = ty;
		ortho.m32 = tz;
		
		return ortho;
		
	}
	
}
