package com.giago.ecard.activity.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.giago.ecard.R;
import com.giago.ecard.activity.intent.EcardIntent;
import com.giago.ecard.service.EcardDao;

public class EcardContextMenu {

    Context context;

    public void registerForContextMenu(Activity activity, View view) {
        activity.registerForContextMenu(view);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        Context ac = v.getContext().getApplicationContext();
        context = v.getContext().getApplicationContext();
        addMenu(menu, 0, ac.getString(R.string.context_menu_preview));
        addMenu(menu, 1, ac.getString(R.string.context_menu_delete));
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
            t.printStackTrace();
            return false;
        }
    }

    private void addMenu(ContextMenu menu, int index, String label) {
        menu.add(0, index, 0, label);
    }

    public void onPreview(long id) {
        Cursor cursor = context.getContentResolver().query(EcardDao.ECARD_URI, null, "_id = ?",
                new String[] { "" + id }, null);
        cursor.moveToFirst();
        EcardIntent ecardIntent = new EcardIntent(cursor, context);
        Intent intent = ecardIntent.getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void onDetele(long id) {
        context.getContentResolver().delete(EcardDao.ECARD_URI, "_id = ? ", new String[] { "" + id });
    }
}
