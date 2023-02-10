package com.example.configremotefirebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            // time to notify
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        // set default when fetch failed
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val json = remoteConfig.getString("config_com_example_config_remote_firebase")
                    if (json.isNotEmpty()) {
                        val jsonSetting = Gson().fromJson<JsonSetting>(json, JsonSetting.type)
                        val commonInfo = jsonSetting.commonInfo!!

                        // display information of object
                        awaitSend.text ="Await Send: ${commonInfo.awaitSend}"
                        maxListen.text ="Max Listen: ${commonInfo.maxListen}"
                        nativeAdCountNew.text ="Native AD Count New: ${commonInfo.nativeAdCountNew}"
                        activeServer.text ="Active Server: ${commonInfo.activeServer}"
                    }
                } else {
                    Toast.makeText(
                        this, "Fetch failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}


