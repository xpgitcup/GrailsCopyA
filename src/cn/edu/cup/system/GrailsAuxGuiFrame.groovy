package cn.edu.cup.system

import groovy.swing.SwingBuilder

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

    def WIDTH = 1200
    def HEIGHT = 768
    int X = (screenSize.width - WIDTH) / 2
    int Y = (screenSize.height - HEIGHT) / 2

    //------------------------------------------------------------------------------------------------------------------
    def grailsAuxDcoument

    //------------------------------------------------------------------------------------------------------------------
    GrailsAuxGuiFrame(GrailsAuxDocument document) {
        grailsAuxDcoument = document
    }

    //------------------------------------------------------------------------------------------------------------------
    def commonAction(ActionEvent evt) {
        def actionName = evt.actionCommand
        println(actionName)
        status.text = actionName
        switch (actionName) {
            case "打开工程目录":
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

        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //工具栏
    def theToolBar = {
        swing.panel(layout: new BorderLayout(), constraints: BorderLayout.NORTH) {
            toolBar(constraints: BorderLayout.NORTH) {
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
                separator()
                separator()
                label(text: "输入源域类")
                //sourceInput = textField(text: grailsAuxDcoument.sourceDomainName)
                button(text: "源域类", actionPerformed: { evt -> commonAction(evt) })
            }
            toolBar(constraints: BorderLayout.SOUTH) {
                label(text: "源：")
                //sourceDomain = label(text: grailsAuxDcoument.sourceDomainName)
                separator()
                //sourceInstance = label(text: grailsAuxDcoument.sourceInstanceName)
                separator()
                separator()
                label("请输入目标域类：")
                //targetInput = textField(text: 'SystemMenu', columns: 20)
                separator()
                separator()
                label(text: "目标：")
                //targetDomain = label(text: "???")
                separator()
                //targetInstance = label(text: "???")
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
