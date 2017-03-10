package cn.edu.cup.system

import groovy.swing.SwingBuilder

import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.UIManager
import java.awt.BorderLayout
import java.awt.Toolkit
import java.awt.event.ActionEvent

/**
 * Created by LiXiaoping on 2017/3/8.
 */
class GrailsAuxGuiFrame {
    //基础GUI设置
    def swing = new SwingBuilder()
    JFrame frame
    def toolkit = Toolkit.getDefaultToolkit()
    def screenSize = toolkit.getScreenSize()

    def WIDTH = 1024
    def HEIGHT = 768
    int X = (screenSize.width - WIDTH) / 2
    int Y = (screenSize.height - HEIGHT) / 2

    //------------------------------------------------------------------------------------------------------------------
    def status
    def currentProject
    //------------------------------------------------------------------------------------------------------------------

    def grailsAuxDcoument

    //------------------------------------------------------------------------------------------------------------------
    GrailsAuxGuiFrame(GrailsAuxDocument document) {
        grailsAuxDcoument = document
    }
    //------------------------------------------------------------------------------------------------------------------
    def openProjectPath = {
        def o = new JFileChooser()
        o.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
        if (grailsAuxDcoument.projectPath) {
            o.setCurrentDirectory(new File(grailsAuxDcoument.projectPath))
        }
        def ok = o.showOpenDialog(null)
        if (ok == JFileChooser.APPROVE_OPTION) {
            grailsAuxDcoument.projectPath = o.getSelectedFile().absolutePath
            currentProject.text = grailsAuxDcoument.projectPath
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    def commonAction(ActionEvent evt) {
        def actionName = evt.actionCommand
        println(actionName)
        status.text = actionName
        switch (actionName) {
            case "打开源工程目录":
                openProjectPath()
                break
            case "输入目标域类":
                inputTargetDomain()
                break
            case "生成目标文档":
                createTargetFile()
                break
            case "源域类":
                changeSourceDomain()
                break
            case "保存设置":
                grailsAuxDcoument.writeConfig()
                break

        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //工具栏
    def theToolBar = {
        swing.panel(layout: new BorderLayout(), constraints: BorderLayout.NORTH) {
            vbox(constraints: BorderLayout.WEST) {
                toolBar {
                    label(text: "操作流程：")
                    grailsAuxDcoument.guideStrings.each { e ->
                        button(text: e, actionPerformed: { evt -> commonAction(evt) })
                        label(text: "->")
                    }
                    separator()
                    label(text: "当前操作:")
                    status = label(text: "")
                    separator()
                    label(text: "当前工程:")
                    currentProject = label(text: "")
                }
                toolBar {
                    label(text: "Hello1")
                }
                toolBar {
                    label(text: "Hello2")
                }

            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    def run() {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        frame = swing.frame(title: 'Grails辅助工具...',
                size: [WIDTH, HEIGHT],
                location: [X, Y],
                defaultCloseOperation: javax.swing.WindowConstants.DISPOSE_ON_CLOSE) {
            theToolBar()
            //mainTabPanel()
        }
        frame.setVisible(true)
    }

}
