package com.giago.ecard.activity.utils;

import com.giago.ecard.R;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;

public abstract class EcardContextMenu {

	public void registerForContextMenu(Activity activity, View view) {
	    activity.registerForContextMenu(view);
	  }

	  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    addMenu(menu, 0, v.getContext().getString(R.string.context_menu_preview));
	    addMenu(menu, 1, v.getContext().getString(R.string.context_menu_delete));
	  }
	  
	  public boolean onContextItemSelected(MenuItem item) {
	    AdapterView.AdapterContextMenuInfo info;
	    try {
	        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
	    } catch (ClassCastException e) {
	      return false;
	    }
	    long id = info.id;
	    try {
	      int i = item.getItemId();
	      switch (i) {
	      case 0:
	        onPreview(id);
	        return true;
	      case 1:
	    	onDetele(id);
	        return true;
	      default:
	        break;
	      }
	      return false;
	    } catch (Throwable t) {
	      return false;
	    }
	  }
	  
	  private void addMenu(ContextMenu menu, int index, String label) {
	    menu.add(0, index, 0, label);
	  }

	  public abstract void onPreview(long id);
	  public abstract void onDetele(long id);
}
