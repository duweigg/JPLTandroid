package com.duwei.jplt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.IOError
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    // Array list of data
    var quit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vocabList = getVocabList()

        recyclerView.adapter = ViewAdapter(vocabList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun getVocabList () : List<ViewData> {

        val items = ArrayList<ViewData>()
        var vocabJs: JSONArray? = null
        var json: String? = null

        try{
            val jsonFile: InputStream = assets.open("vocab.json")
            json = jsonFile.bufferedReader().use{it.readText()}
            vocabJs = JSONArray(json)
        } catch (e:IOError){

        }

        for(i in 0 until 20){
            val randomIndex =(Math.random()* (vocabJs?.length() ?: 0)).toInt()
            val singleVocab = vocabJs?.getJSONObject(randomIndex)
            //Log.e("JSON Parser","vocab "+ singlevocab.getString("word"));
            val vocab = singleVocab?.getString("word")
            val kanji = singleVocab?.getString("kanji")
            val meaning = singleVocab?.getString("meaning")
            val type = singleVocab?.getString("type")
            val times = singleVocab?.getString("times")
            if (vocab !== null && times !== null && type !== null && kanji !== null && meaning !== null){
                val item = ViewData(vocab, times, type, kanji, meaning)
                items += item
            }
        }

        return items
    }
}

//class MainActivity : AppCompatActivity() {
//
//    // Array list of data
//    var quit = false
//    var kanji:ArrayList<String> = ArrayList()
//    var vocab:ArrayList<String> = ArrayList()
//    var meaning:ArrayList<String> = ArrayList()
//    var type:ArrayList<String> = ArrayList()
//    var times:ArrayList<String> = ArrayList()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
//
//        var linearLayoutManager = LinearLayoutManager(applicationContext)
//        recyclerView.layoutManager = linearLayoutManager
//
//
//        val obj = JSONObject(loadJSONFromAsset())
//        val vocabArray = obj.getJSONArray("vocab")
//        for(i in 0 until 20){
//            var random_index =(Math.random()*vocabArray.length()).toInt()
//            var singlevocab = vocabArray.getJSONObject(random_index)
//            //Log.e("JSON Parser","vocab "+ singlevocab.getString("word"));
//            vocab.add(singlevocab.getString("word"))
//            kanji.add(singlevocab.getString("kanji"))
//            meaning.add(singlevocab.getString("meaning"))
//            type.add(singlevocab.getString("type"))
//            times.add(singlevocab.getString("times"))
//        }
//
//        var vocabAdapter = vocabAdapter(applicationContext,vocab,kanji,meaning,type,times)
//        recyclerView.adapter = vocabAdapter
//
//        val NextButton = findViewById<View>(R.id.button2) as Button
//        NextButton.setOnClickListener {
//            vocab.clear()
//            kanji.clear()
//            meaning.clear()
//            type.clear()
//            times.clear()
//
//            for(i in 0 until 20){
//                var random_index =(Math.random()*vocabArray.length()).toInt()
//                var singlevocab = vocabArray.getJSONObject(random_index)
//                //Log.e("JSON Parser","vocab "+ singlevocab.getString("word"));
//                vocab.add(singlevocab.getString("word"))
//                kanji.add(singlevocab.getString("kanji"))
//                meaning.add(singlevocab.getString("meaning"))
//                type.add(singlevocab.getString("type"))
//                times.add(singlevocab.getString("times"))
//            }
//
//            var vocabAdapter = vocabAdapter(applicationContext,vocab,kanji,meaning,type,times)
//            recyclerView.adapter = vocabAdapter
//        }
//
//    }
//
//    // Disable back key
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//
//        val timer = Timer()
//        val t = object : TimerTask() {
//            override fun run() {
//                quit = false
//            }
//        }
//
//        when(keyCode){
//            KeyEvent.KEYCODE_BACK ->{
//                if(true == quit){
//                    finish()
//                }else{
//                    Toast.makeText(this.applicationContext,"press back again to quit JPLT", Toast.LENGTH_SHORT).show()
//                    quit = true
//                    timer.schedule(t, 2000)
//                }
//                return true
//            }
//
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//
//
//    fun loadJSONFromAsset(): String? {
//        var json : String? = null
//        try {
//            val `is` = assets.open("vocab.json")
//            val size = `is`.available()
//            val buffer = ByteArray(size)
//            `is`.read(buffer)
//            `is`.close()
//            json = String(buffer, charset("UTF-8"))
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//            return null
//        }
//
//        return json
//    }
//}
