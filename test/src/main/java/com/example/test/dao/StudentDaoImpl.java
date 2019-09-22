package com.example.test.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.test.entity.Student;
import com.example.test.provider.MyContentProvider;
import com.example.test.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private DBUtil dbUtil;
    private SQLiteDatabase db;
    private Context context;

    public StudentDaoImpl(Context context) {
        this.context = context;
        dbUtil = new DBUtil(context);
    }

    @Override
    public List<Student> selectAllStudents() {
        List<Student> students = null;
        db = dbUtil.getReadableDatabase();
        String sql = "select * from goto_student";
//        Cursor cursor = db.rawQuery(sql, null);
        //从ContentProvider查询
        Cursor cursor = context.getContentResolver().query(MyContentProvider.STUDENT_URI,null,null,null,null);
        if (cursor != null && cursor.getCount() > 0) {
            students = new ArrayList<>();
            while (cursor.moveToNext()) {
                Student student1 = new Student();
                student1.setGoto_id(cursor.getInt(cursor.getColumnIndex("goto_id")));
                student1.setName(cursor.getString(cursor.getColumnIndex("name")));
                student1.setClassmate(cursor.getString(cursor.getColumnIndex("classmate")));
                student1.setAge(cursor.getInt(cursor.getColumnIndex("age")));

                students.add(student1);
            }
            cursor.close();
        }
        return students;
    }

    @Override
    public void insert(Student student) {
        db = dbUtil.getWritableDatabase();
////        String sql = "insert into goto_student values(null,?,?,?)";
////        db.execSQL(sql, new Object[]{
////                student.getName(),
////                student.getClassmate(),
////                student.getAge()});
        ContentValues values = new ContentValues();
        values.put("name",student.getName());
        values.put("classmate",student.getClassmate());
        values.put("age",student.getAge());

        Uri uri = context.getContentResolver().insert(MyContentProvider.STUDENT_URI,values);
        Log.i("SQLiteDemo1",uri != null ? uri.toString():"");
        db.close();
    }

    @Override
    public void update(Student student) {
        db = dbUtil.getWritableDatabase();
//        String sql = "update goto_student set name=? where classmate=?";
//        db.execSQL(sql, new Object[]{
//                student.getName(),
//                student.getClassmate(),
//        });
        ContentValues values = new ContentValues();
        values.put("name",student.getName());
        values.put("classmate",student.getClassmate());
        values.put("age",student.getAge());

        context.getContentResolver().update(MyContentProvider.STUDENT_URI,values,"goto_id=?",new String[]{String.valueOf(student.getGoto_id())});
    }

    @Override
    public void delete(String studentName) {
        db = dbUtil.getWritableDatabase();
        String sql = "delete from goto_student where name=?";
        db.execSQL(sql, new Object[]{studentName});
    }

    @Override
    public void delete(int _id) {
//        db = dbUtil.getWritableDatabase();
//        String sql = "delete from goto_student where name=?";
//        db.execSQL(sql, new Object[]{studentName});

        context.getContentResolver().delete(MyContentProvider.STUDENT_URI,"goto_id=?",new String[]{String.valueOf(_id)});
    }
}
