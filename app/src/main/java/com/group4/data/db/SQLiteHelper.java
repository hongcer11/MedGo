package com.group4.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.SQLException;

import com.group4.data.model.User;

import java.io.*;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "medical_data.db"; // tên file trong assets
    private static String DB_PATH = ""; // sẽ gán khi khởi tạo
    private final Context context;
    private SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }

    /**
     * Gọi hàm này trong MainActivity để đảm bảo database đã copy từ assets
     */
    public void createDatabaseIfNeeded() throws IOException {
        boolean dbExists = checkDatabaseExists();
        if (!dbExists) {
            this.getReadableDatabase(); // tạo file rỗng
            copyDatabaseFromAssets();   // chép đè file từ assets
        }
    }

    private boolean checkDatabaseExists() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDatabaseFromAssets() throws IOException {
        InputStream input = context.getAssets().open(DB_NAME);
        File dbDir = new File(DB_PATH);
        if (!dbDir.exists()) dbDir.mkdirs();
        OutputStream output = new FileOutputStream(DB_PATH + DB_NAME);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        input.close();
    }

    public void openDatabase() throws SQLException {
        String fullPath = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(fullPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Không cần tạo bảng nếu dùng file .db có sẵn
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Có thể xử lý sau nếu nâng cấp DB
    }

//    public User getUserByPhoneAndPassword(String phone, String password) {
//        User user = null;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(
//                "SELECT * FROM user WHERE phone_numb = ? AND password = ?",
//                new String[]{phone, password}
//        );
//
//        if (cursor != null && cursor.moveToFirst()) {
//            int id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
//            String fullName = cursor.getString(cursor.getColumnIndexOrThrow("full_name"));
//            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
//            String gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));
//            String dob = cursor.getString(cursor.getColumnIndexOrThrow("dob"));
//            String status = cursor.getString(cursor.getColumnIndexOrThrow("Status"));
//            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("phone_number"));
////            String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
//
//            user = new User(id, fullName, phoneNumber, email, password, gender, dob, status);
//
//            cursor.close();
//        }
//
//        return user;
//    }
public User getUserByPhoneAndPassword(String phone, String password) {
    User user = null;
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.rawQuery(
            "SELECT * FROM user WHERE phone_number = ? AND password = ?",
            new String[]{phone, password}
    );

    if (cursor != null && cursor.moveToFirst()) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
        String fullName = cursor.getString(cursor.getColumnIndexOrThrow("full_name"));
        String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
        String gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));
        String dob = cursor.getString(cursor.getColumnIndexOrThrow("dob"));
        String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
        String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("phone_number"));

        user = new User(id, fullName, email, password, dob, phoneNumber, gender, status);

        cursor.close();
    }

    return user;
}
}
