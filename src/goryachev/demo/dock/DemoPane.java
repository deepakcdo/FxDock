// Copyright (c) 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.demo.dock;
import goryachev.common.util.Hex;
import goryachev.fx.FX;
import goryachev.fx.HPane;
import goryachev.fxdock.FxDockPane;
import java.text.DecimalFormat;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * DemoDockPane.
 */
public class DemoPane
    extends FxDockPane
{
	private static int seq = 1;
	private int pseq;
	
	
	public DemoPane(String type)
	{
		super(type);
		
		VBox vb = new VBox();
//		a(vb, 2, HPane.PREF, HPane.FILL, HPane.PREF);
//		a(vb, 2, HPane.FILL, HPane.FILL, HPane.FILL);
//		a(vb, 2, 20, HPane.FILL, 20);
		a(vb, 2, HPane.FILL, HPane.FILL, HPane.FILL, HPane.FILL, HPane.FILL, HPane.FILL, HPane.FILL, HPane.FILL, HPane.FILL, HPane.FILL);
		
		BorderPane bp = new BorderPane();
		bp.setCenter(createColorNode(type));
		bp.setBottom(vb);
		
		setCenter(bp);
		this.pseq = seq++;
		setTitle("pane " + pseq);
	}
	
	
	private static void a(Pane p, int gap, double ... specs)
	{
		HPane hp = new HPane();
		int ix = 0;
		for(double w: specs)
		{
			Color c = Color.gray(0.5 + 0.5 * ix / (specs.length - 1));
			String text = getDescription(w);
			TextField t = new TextField(text);
			t.setEditable(false);
			t.setPrefColumnCount(3);
			t.setTooltip(new InfoTooltip(t));
			t.setBackground(FX.background(c));
			t.setPadding(Insets.EMPTY);
			hp.add(t, w);
			ix++;
		}
		p.getChildren().add(hp);
	}


	private static String getDescription(double w)
	{
		if(w == HPane.FILL)
		{
			return "fill";
		}
		else if(w == HPane.PREF)
		{
			return "pref";
		}
		else
		{
			return new DecimalFormat("#0.##").format(w);
		}
	}


	private Node createColorNode(String c)
	{
		int rgb = Hex.parseInt(c, 0);
		Region r = new Region();
		r.setBackground(new Background(new BackgroundFill(FX.rgb(rgb), null, null)));
		return r;
	}
}
