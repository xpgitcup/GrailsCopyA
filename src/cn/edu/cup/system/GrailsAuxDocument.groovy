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
            "拷贝文件",
            "保存设置"
    ]

    def projectSetting = [
            "sourcePath":"",
            "sourceProject": "",
            "targetPath": "",
            "targetProject": "",
    ]

    def getSourcePath() {
        return projectSetting.sourcePath
    }

    def setSourcePath(String p) {
        projectSetting.sourcePath = p
    }

    def getSourceProject() {
        return projectSetting.sourceProject
    }

    def setSourceProject(String p) {
        projectSetting.sourceProject = p
    }

    def getTargetPath() {
        return projectSetting.targetPath
    }

    def setTargetPath(String p) {
        projectSetting.targetPath = p
    }

    def getTargetProject() {
        return projectSetting.targetProject
    }

    def setTargetProject(String p) {
        projectSetting.targetProject = p
    }

    def loadConfig() {
        Properties p = new Properties()
        def sf = new File("GrailsCopyA.ini")
        if (sf.exists()) {
            sf.withInputStream { stream->
                if (stream) {
                    p.load(stream)
                    projectSetting.each() { e->
                        projectSetting.put(e.key, p.getProperty(e.key))
                    }
                }
            }
        }
    }

    def writeConfig() {
        Properties p = new Properties()
        new File("GrailsCopyA.ini").withOutputStream { stream->
            projectSetting.each() { e->
                if (e.value) {
                    p.setProperty(e.key, e.value)
                }
            }
            p.store(stream, "Grails Copy A")
        }

    }

    GrailsAuxDocument() {
        loadConfig()
    }

}
