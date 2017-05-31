package com.example.olya.myapplication;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  {

    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] read_from = { ContactsContract.Contacts.DISPLAY_NAME};
        int[] display_to = { R.id.user_name};
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.contact_list_item, null,
                read_from, display_to, 0);
        getSupportLoaderManager().initLoader(1, new Bundle(), contactsLoader);
        ListView listContacts = (ListView) findViewById(R.id.list_contacts);
        listContacts.setAdapter(simpleCursorAdapter);
    }

    private LoaderManager.LoaderCallbacks<Cursor> contactsLoader =
            new LoaderManager.LoaderCallbacks<Cursor>() {

                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {

                    String[] fields = new String[] { ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.DISPLAY_NAME};

                    return new CursorLoader(MainActivity.this,
                            ContactsContract.Contacts.CONTENT_URI, fields, null, null, null);
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                    simpleCursorAdapter.swapCursor(cursor);
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    simpleCursorAdapter.swapCursor(null);
                }
            };
}
