package com.example.expensemanger;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDataBase {

	public static final String Key_Day = "Day";
	public static final String Key_Tag = "Tag" ;
	public static final String Key_Month = "Month";
	public static final String Key_Year = "Year";
	public static final String Key_Food = "Food";
	public static final String Key_Bill = "Bill";
	public static final String Key_Medical = "Medical";
	public static final String Key_Transport = "Transport";	
	public static final String Key_Others = "Others";

	private static final String DBNAME = "Expense_Manger";
	private static final String TABLENAME = "Expenses";
	private static final int DBVERSION = 1;

	private DBHelper DbHelper;
	private Context context;
	private SQLiteDatabase MyDb;

	private class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context, DBNAME, null, DBVERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			db.execSQL("CREATE TABLE " + TABLENAME + " (  "  + Key_Tag + " INTEGER PRIMARY KEY AUTOINCREMENT," + Key_Day + " INTEGER NOT NULL, "
					+ Key_Month + " TEXT NOT NULL, " + Key_Year + " INTEGER NOT NULL, " + Key_Food + " INTEGER NOT NULL, "
					+ Key_Bill + " INTEGER NOT NULL, " + Key_Medical + " INTEGER NOT NULL, "
					+ Key_Transport + " INTEGER NOT NULL, " + Key_Others + " INTEGER NOT NULL )");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP IF EXISTS" + TABLENAME);
			onCreate(db);
		}

	}

	public MyDataBase(Context c) {
		this.context = c;
	}

	public MyDataBase Open() {
		
		DbHelper = new DBHelper(context);
		MyDb = DbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DbHelper.close();
	}

	public void deletebyDate(int tag) {
		MyDb.delete(TABLENAME, Key_Tag + " = " + tag, null);
	}

	public void deletebyName(String month) {
		String[] whereargs = { month } ;
		try {
			MyDb.delete(TABLENAME, Key_Month + "=?", whereargs);
		} catch (Exception e) {
			Log.e("Search Error", e.getMessage());
		}

	}

	public Boolean IsTableEmpty(){
		String[] columns = new String[] {Key_Tag,Key_Day,Key_Month,Key_Year,Key_Food, Key_Bill,Key_Medical,Key_Transport,Key_Others };
		Cursor mycursor = MyDb.query(TABLENAME, columns, null, null, null,
				null, null);
		if (mycursor.isAfterLast())
			return true ;
		else
			return false ;
	}
	
	public void AddEntry(String day,String month,String year, String food, String bill,String medical,String trans,String other ) {
		ContentValues cV = new ContentValues();
		cV.put(Key_Day, day);
		cV.put(Key_Month, month);
		cV.put(Key_Year, year);
		cV.put(Key_Food, food);
		cV.put(Key_Bill, bill);
		cV.put(Key_Medical, medical);
		cV.put(Key_Transport, trans);
		cV.put(Key_Others,other);
		MyDb.insert(TABLENAME, null, cV);
	}
	
	public void AddEntry(int day,String month,long year, int food, int bill,int medical,int trans,int other ) {
		ContentValues cV = new ContentValues();
		cV.put(Key_Day, day);
		cV.put(Key_Month, month);
		cV.put(Key_Year, year);
		cV.put(Key_Day, day);
		cV.put(Key_Food, food);
		cV.put(Key_Bill, bill);
		cV.put(Key_Medical, medical);
		cV.put(Key_Transport, trans);
		cV.put(Key_Others,other);
		MyDb.insert(TABLENAME, null, cV);
	}

	public MyData getData(String dat) {

		MyData myData = new MyData() ;
		String[] columns = new String[] {Key_Tag, Key_Day, Key_Month, Key_Year, Key_Food, Key_Bill,Key_Medical,Key_Transport,Key_Others };
		String[] cdate = dat.split("/") ;
		Cursor mycursor = MyDb.query(TABLENAME, columns,Key_Day + " = " + cdate[0] + " AND " + Key_Month + " = '" + cdate[1] + "' AND " + Key_Year + " = " + cdate[2] , null, null,null, null);

		int iTag = mycursor.getColumnIndex(Key_Tag) ;
		int iDay = mycursor.getColumnIndex(Key_Day);
		int iMonth = mycursor.getColumnIndex(Key_Month) ;
		int iYear = mycursor.getColumnIndex(Key_Year) ;
		int iFood = mycursor.getColumnIndex(Key_Food);
		int iBill = mycursor.getColumnIndex(Key_Bill);
		int imedical = mycursor.getColumnIndex(Key_Medical);
		int iTrans = mycursor.getColumnIndex(Key_Transport);
		int iOther = mycursor.getColumnIndex(Key_Others);

		

		for (mycursor.moveToFirst(); !mycursor.isAfterLast(); mycursor
				.moveToNext()) {
			int tag = Integer.valueOf(mycursor.getString(iTag));
			int day= Integer.valueOf(mycursor.getString(iDay));
			String month = mycursor.getString(iMonth);
			long year = Integer.valueOf(mycursor.getString(iYear));
			int food = Integer.valueOf(mycursor.getString(iFood));
			int bill = Integer.valueOf(mycursor.getString(iBill));
			int medical = Integer.valueOf(mycursor.getString(imedical));
			int trans = Integer.valueOf(mycursor.getString(iTrans));
			int other = Integer.valueOf(mycursor.getString(iOther));
			Date date = new Date(day,month,year) ;
			Expenses expense = new Expenses(food,bill,medical,trans,other) ;
			myData = new MyData(tag,date,expense);
		}

		return myData;
	}
	

	public void UpdateDB(MyData data,Context c){
		ContentValues cV = new ContentValues() ;
		
		cV.put(Key_Day, data.getDate().getDay()) ;
		cV.put(Key_Month, data.getDate().getMonth()) ;
		cV.put(Key_Year, data.getDate().getYear()) ;
		cV.put(Key_Food, data.getExpnses().getFood()) ;
		cV.put(Key_Bill, data.getExpnses().getBill()) ;
		cV.put(Key_Medical, data.getExpnses().getMedical()) ;
		cV.put(Key_Transport, data.getExpnses().getTransport()) ;
		cV.put(Key_Others, data.getExpnses().getOthers()) ;
		
		String[] dates = {data.getDate().getDate()} ;
		MyDb.update(TABLENAME, cV, Key_Bill + "=?",dates) ;
	}

	public List<String> GetDates(String month) {
		List<String> dates = new ArrayList<String>() ;
		String [] colums = {Key_Day} ;
		String[] temp = month.split("/") ;
		Cursor mycursor = MyDb.query(TABLENAME,colums, Key_Year + " = " + Integer.valueOf(temp[1]) + " AND " + Key_Month + " = " + "'"+ temp[0] + "'" ,null,null,null,null) ;
		int iDay = mycursor.getColumnIndex(Key_Day);
		for (mycursor.moveToFirst(); !mycursor.isAfterLast(); mycursor
				.moveToNext()) {
			String day= mycursor.getString(iDay);
			dates.add(day) ;			
		}
		 
		return dates ;
	}

	 @SuppressLint("NewApi") public List<String> GetMnths() {
		
		List<String> months = new ArrayList<String>() ;
		String [] colums = {Key_Month,Key_Year} ;
		Cursor mycursor = MyDb.query(true, TABLENAME,colums ,null,null,null,null, null, null, null) ;
		int imnth = mycursor.getColumnIndex(Key_Month);
		int iyear = mycursor.getColumnIndex(Key_Year) ;
		for (mycursor.moveToFirst(); !mycursor.isAfterLast(); mycursor
				.moveToNext()) {
			String mnth = mycursor.getString(imnth);
			String year = mycursor.getString(iyear);
			months.add(mnth + " " + year) ;		
	}
		return months ;
	}

	public void deletebyDate(String string) {
		// TODO Auto-generated method stub
		String args[] = string.split("/") ;
		MyDb.delete(TABLENAME, Key_Day + " = " +args[0] + " AND " + Key_Month + " = '" + args[1] + "' AND " + Key_Year + " = " + args[2], null) ;
	}
}
