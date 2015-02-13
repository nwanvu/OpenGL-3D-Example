package com.nwanvu.example.opengl3d;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.nwanvu.example.opengl3d.obj.Cube;
import com.nwanvu.example.opengl3d.obj.Square;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * OpenGL Custom renderer used with GLSurfaceView
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {
	Context context; // Application's context

	private Square square;
	private Cube cube;
	private Cube cube1;
	private Cube cube2;

	private static float angleCube = 0;
	private static float speedCube = -1.5f;

	// Constructor with global application context
	public MyGLRenderer(Context context) {
		this.context = context;

		square = new Square();

		cube = new Cube();
		cube1 = new Cube();
		cube2 = new Cube();
	}

	// Call back when the surface is first created or re-created
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Set color's clear-value
		// to
		// // black
		// gl.glClearDepthf(1.0f); // Set depth's clear-value to farthest
		// gl.glEnable(GL10.GL_DEPTH_TEST); // Enables depth-buffer for hidden
		// // surface removal
		// gl.glDepthFunc(GL10.GL_LEQUAL); // The type of depth testing to do
		// gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); //
		// nice
		// // perspective
		// // view
		// gl.glShadeModel(GL10.GL_SMOOTH); // Enable smooth shading of color
		// gl.glDisable(GL10.GL_DITHER); // Disable dithering for better
		// // performance

		// You OpenGL|ES initialization code here
		// ......

		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0, 0, 0, 0);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glEnable(GL10.GL_DEPTH_TEST);
	}

	// Call back after onSurfaceCreated() or whenever the window's size changes
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if (height == 0)
			height = 1; // To prevent divide by zero
		float aspect = (float) width / height;

		// Set the viewport (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
		gl.glLoadIdentity(); // Reset projection matrix
		// Use perspective projection
		GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); // Select model-view matrix
		gl.glLoadIdentity(); // Reset

	}

	// Call back to draw the current frame.
	@Override
	public void onDrawFrame(GL10 gl) {
		// Clear color and depth buffers using clear-value set earlier
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -6.0f);
		//square.draw(gl);

		// ----- Render the Color Cube -----
		gl.glLoadIdentity(); // Reset the model-view matrix
		gl.glTranslatef(-2.5f, 0.0f, -20.0f); // Translate right and into the
												// screen
		gl.glRotatef(angleCube, 1.0f, 0f, 0f); // rotate about the axis (1,1,1)
												// (NEW)
		cube.draw(gl); // Draw the cube (NEW)
		// Update the rotational angle after each refresh (NEW)
		angleCube += speedCube; // (NEW)

		// / cube2
		gl.glLoadIdentity(); // Reset the model-view matrix
		gl.glTranslatef(0.0f, 0.0f, -20.0f);
		gl.glRotatef(angleCube * 2, 1.0f, 0f, 0f);
		cube1.draw(gl);

		// / cube3
		gl.glLoadIdentity(); // Reset the model-view matrix
		gl.glTranslatef(2.5f, 0.0f, -20.0f);
		gl.glRotatef(angleCube * 4, 1.0f, 0f, 0f);
		cube2.draw(gl);

	}
}
