package tn.org.mygiphy.data.local

//
//@Database(
//    entities = [GifEntity::class],
//    version = 1,
//    exportSchema = false
//)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun gifDao(): FavoriteDao
//
//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//
//        fun getInstance(context: Context) = instance ?: synchronized(this) {
//            instance ?: Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "favorite.db"
//            ).build()
//        }
//    }
//}