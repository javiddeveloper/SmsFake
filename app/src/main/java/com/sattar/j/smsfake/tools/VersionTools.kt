package com.sattar.j.smsfake.tools

import com.sattar.j.smsfake.BuildConfig
import com.sattar.j.smsfake.data.entity.Version

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Sep -- 1:56 AM
 */
class VersionTools {
    companion object {

        fun appVersionName(): String {
            return BuildConfig.VERSION_NAME
        }

        fun appVersionNameByInt(): Int {
            return Integer.valueOf(BuildConfig.VERSION_NAME.replace(".", ""))
        }

        fun appVersionNameParserByInt(version: String): Int {
            return Integer.valueOf(version.replace(".", ""))
        }

        fun appVersionCode(): Int {
            return BuildConfig.VERSION_CODE
        }

        fun needForUpdate(version: Version): Boolean {
            return (Integer.valueOf(version.versionName.replace(".", ""))
                    > Integer.valueOf(BuildConfig.VERSION_NAME.replace(".", "")))
        }


    }
}