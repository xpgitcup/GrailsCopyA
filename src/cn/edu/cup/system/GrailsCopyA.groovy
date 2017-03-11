package cn.edu.cup.system

/**
 * Created by LiXiaoping on 2017/3/8.
 */
class GrailsCopyA {
    static void main(String[] args) {
        println("Grails 辅助拷贝工具.")

        def document = new GrailsAuxDocument()
        def grailsAuxGuiFrame = new GrailsAuxGuiFrame(document)
        grailsAuxGuiFrame.run()
    }
}
