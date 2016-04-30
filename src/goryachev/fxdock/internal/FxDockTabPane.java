// Copyright (c) 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.fxdock.internal;
import goryachev.common.util.CList;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


/**
 * FxDockTabPane.
 */
public class FxDockTabPane
	extends TabPane
{
	protected final ReadOnlyObjectWrapper<Node> parent = new ReadOnlyObjectWrapper<Node>();

	
	public FxDockTabPane()
	{
	}
	
	
	public final ReadOnlyProperty<Node> dockParentProperty()
	{
		return parent.getReadOnlyProperty();
	}
	
	
	public Node getTab(int ix)
	{
		if(ix >= 0)
		{
			ObservableList<Tab> ps = getTabs();
			if(ix < ps.size())
			{
				return ps.get(ix).getContent();
			}
		}
		return null;
	}
	
	
	public void addTab(Node n)
	{
		Tab t = new Tab(null, n);
		getTabs().add(t);
		DockTools.setParent(this, n);
	}
	
	
	public void addTab(int ix, Node n)
	{
		Tab t = new Tab(null, n);
		getTabs().add(ix, t);
		DockTools.setParent(this, n);
	}
	
	
	public Node removeTab(int ix)
	{
		Tab t = getTabs().remove(ix);
		Node n = t.getContent();
		DockTools.setParent(null, n);
		return n;
	}
	
	
	public int getTabCount()
	{
		return getTabs().size();
	}
	
	
	public List<Node> getPanes()
	{
		CList<Node> rv = new CList(getTabCount());
		for(Tab t: getTabs())
		{
			rv.add(t.getContent());
		}
		return rv;
	}


	public int indexOfTab(Node n)
	{
		ObservableList<Tab> ts = getTabs();
		for(int i=ts.size()-1; i>=0; --i)
		{
			Tab t = ts.get(i);
			if(t.getContent() == n)
			{
				return i;
			}
		}
		return -1;
	}
}
