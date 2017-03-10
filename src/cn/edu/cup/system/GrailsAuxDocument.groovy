package cn.edu.cup.system

/**
 * Created by LiXiaoping on 2017/3/8.
 */
class GrailsAuxDocument {
    //这是主要的几个步骤
    def guideStrings = [
            "打开源工程目录",
            "打开目标工程目录",
            "输入目标工程名",
            "拷贝文件"
    ]

    def projectPath

    def setProjectPath(String p) {
        projectPath = p
        writeConfig()
    }

    def loadConfig() {
        Properties p = new Properties()
        def sf = new File("GrailsCopyA.ini")
        if (sf.exists()) {
            sf.withInputStream { stream->
                if (stream) {
                    p.load(stream)
                    projectPath = p.getProperty("projectPath")
                }
            }
        }
    }

    def writeConfig() {
        Properties p = new Properties()
        p.setProperty("projectPath", projectPath)
        new File("GrailsCopyA.ini").withOutputStream { stream->
            p.store(stream, "Grails Copy A")
        }

    }

    GrailsAuxDocument() {
        loadConfig()
    }

}
