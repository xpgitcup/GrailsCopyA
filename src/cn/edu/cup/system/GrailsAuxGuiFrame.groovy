package cn.edu.cup.system

import groovy.swing.SwingBuilder

import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.UIManager
import java.awt.BorderLayout
import java.awt.GridLayout
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
    def sourcePath
    def sourceProject
    def targetPath
    def targetProject
    def console
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
        if (grailsAuxDcoument.sourcePath) {
            o.setCurrentDirectory(new File("${grailsAuxDcoument.sourcePath}/${grailsAuxDcoument.sourceProject}"))
        }
        def ok = o.showOpenDialog(null)
        if (ok == JFileChooser.APPROVE_OPTION) {
            grailsAuxDcoument.sourcePath = o.getSelectedFile().parent
            grailsAuxDcoument.sourceProject = o.getSelectedFile().name
            sourcePath.text = grailsAuxDcoument.sourcePath
            sourceProject.text = grailsAuxDcoument.sourceProject
        }
    }

    def openTargetPath = {
        def o = new JFileChooser()
        o.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
        if (grailsAuxDcoument.targetPath) {
            o.setCurrentDirectory(new File(grailsAuxDcoument.targetPath))
        }
        def ok = o.showOpenDialog(null)
        if (ok == JFileChooser.APPROVE_OPTION) {
            grailsAuxDcoument.targetPath = o.getSelectedFile().path
            targetPath.text = grailsAuxDcoument.targetPath
        }
    }

    def inputTargetProject = {
        grailsAuxDcoument.targetProject = targetProject.text
    }

    def copyFiles() {
        
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
            case "打开目标工程目录":
                openTargetPath()
                break
            case "输入目标工程名":
                inputTargetProject()
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
        swing.panel(layout: new GridLayout(3,1), constraints: BorderLayout.NORTH) {
            toolBar {
                label(text: "操作流程：")
                grailsAuxDcoument.guideStrings.each { e ->
                    button(text: e, actionPerformed: { evt -> commonAction(evt) })
                    label(text: "->")
                }
                separator()
                label(text: "当前操作:")
                status = label(text: "")
            }
            toolBar {
                label(text: "源目录：")
                sourcePath = label(text: "")
                separator()
                label(text: "源工程：")
                sourceProject = label(text: "")
            }
            toolBar {
                label(text: "目标目录：")
                targetPath = label(text: "")
                separator()
                label(text: "目标工程：")
                targetProject = textField(text: grailsAuxDcoument.targetProject)
            }
        }
    }

    //主面板
    def mainPanel = {
        swing.panel(layout: new GridLayout(1,1), constraints: BorderLayout.CENTER) {
            console = textArea()
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
            mainPanel()
        }
        frame.setVisible(true)
    }

}
