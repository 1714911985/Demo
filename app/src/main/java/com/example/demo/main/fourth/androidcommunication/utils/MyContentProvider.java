package com.example.demo.main.fourth.androidcommunication.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.demo.utils.MySQLiteHelper;

public class MyContentProvider extends ContentProvider {
    private MySQLiteHelper dbHelper;
    private final static int userDir = 0;
    private final static int userItem = 1;
    private UriMatcher uriMatcher;
    private final static String authority = "com.example.demo.provider";


    public MyContentProvider() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authority, "user", userDir);
        uriMatcher.addURI(authority, "user/#", userItem);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (match) {
            case userDir:
                deleteRows = db.delete("user", selection, selectionArgs);
                break;
            case userItem:
                String userId = uri.getPathSegments().get(1);
                deleteRows = db.delete("user", "id = ?", new String[]{userId});
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case userDir:
                return "vnd.android.cursor.dir/vnd." + authority + ".user";
            case userItem:
                return "vnd.android.cursor.item/vnd." + authority + ".user";
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri newUri = null;
        switch (match) {
            case userDir:
            case userItem:
                long userId = db.insert("User", null, values);
                newUri = Uri.parse("content://" + authority + "/user/" + userId);
                break;
            default:
                break;
        }
        return newUri;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MySQLiteHelper(getContext(), "Login.db", null, 1);
        Log.d("MyContentProvider","onCreate");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor queryCursor = null;
        switch (match) {
            case userDir:
                queryCursor = db.query("User", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case userItem:
                String userId = uri.getPathSegments().get(1);
                queryCursor = db.query("User", projection, "id = ?", new String[]{userId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return queryCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (match) {
            case userDir:
                updateRows = db.update("User", values, selection, selectionArgs);
                break;
            case userItem:
                String userId = uri.getPathSegments().get(1);
                updateRows = db.update("User", values, "id = ?", new String[]{userId});
                break;
            default:
                break;
        }
        return updateRows;
    }
}