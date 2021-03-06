package org.avm.hmi.swt.desktop;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class VerticalQualityLevel extends Composite implements PaintListener {

	private Canvas canvas = null;

	private int _numBar = 5;

	private int _quality;

	private Display _display;

	private Color _fgcolor;

	private Color _bgcolor;

	public VerticalQualityLevel(Composite parent, int style) {
		super(parent, style);
		_display = parent.getDisplay();
		_fgcolor = _display.getSystemColor(SWT.COLOR_DARK_RED);
		_bgcolor = _display.getSystemColor(SWT.COLOR_BLACK);
		initialize();
	}

	public void setEnabled(boolean b) {
		super.setEnabled(b);
		if (!b) {
			_bgcolor = _display.getSystemColor(SWT.COLOR_GRAY);
		} else {
			_bgcolor = _display.getSystemColor(SWT.COLOR_BLACK);
		}
	}

	private void initialize() {
		createCanvas();
		draw(null);
		GridLayout grid = new GridLayout();
		grid.horizontalSpacing = 0;
		grid.marginWidth = 0;
		grid.marginHeight = 0;
		grid.verticalSpacing = 0;
		grid.numColumns = 1;
		setLayout(grid);
	}

	/**
	 * This method initializes canvas
	 * 
	 */
	private void createCanvas() {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		canvas = new Canvas(this, SWT.NONE);
		canvas.setBackground(DesktopStyle.getBackgroundColor());
		canvas.addPaintListener(this);
		canvas.setLayoutData(gridData);
	}

	private void draw(Rectangle b) {
		Rectangle bounds = null;
		if (b == null) {
			bounds = canvas.getBounds();
		} else {
			bounds = b;
		}
		bounds.width -= 1;
		// size.y -= 2;
		GC gc = new GC(canvas);
		gc.setForeground(_display.getSystemColor(SWT.COLOR_BLUE));
		double x = 0, dx = bounds.width, dy, y = bounds.height;
		// int sep = (size.y / 25);
		// sep=1;
		// dy = ((size.y - ((_numBar - 1) * sep)) / _numBar);
		dy = 4;

		Rectangle rect;
		double hsize = 2;
		double dxmin = 10;
		double f = (dx - dxmin) / (double) _numBar;
		for (int i = _numBar; i > 0; i--) {
			// y = size.y - (i * dy + (i - 1) * hsize);
			y = (i * dy + (i - 1) * hsize);
			// x=(f)*(_numBar-i)+dxmin;
			rect = new Rectangle((int) x, (int) y, (int) (dx), (int) dy);
			if (i <= _quality) {
				gc.setBackground(_fgcolor);
			} else {
				gc.setBackground(DesktopStyle.getBackgroundColor());
			}
			gc.fillRectangle(rect);
			gc.setForeground(_bgcolor);
			gc.drawRectangle(rect);
		}
		gc.dispose();
	}

	public void setForegroundColor(Color color) {
		_fgcolor = color;
	}

	public void setBackgroundColor(Color color) {
		_bgcolor = color;
	}

	public void setNumberOfBar(int number) {
		_numBar = number;
	}

	public void setQuality(int quality) {
		_quality = quality;
		draw(null);
	}

	public void paintControl(PaintEvent e) {

		draw( ((Canvas) e.widget).getBounds());
	}

} // @jve:decl-index=0:visual-constraint="10,10"
