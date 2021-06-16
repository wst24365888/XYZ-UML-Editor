# XYZ UML Editor v2

![Java](https://img.shields.io/badge/with-Java-red?logo=Java)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/50d53b59385f444eaeb5ddb5bffc9315)](https://www.codacy.com/gh/wst24365888/XYZ-UML-Editor/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=wst24365888/XYZ-UML-Editor&amp;utm_campaign=Badge_Grade)

## Screenshots

![screenshot-1.PNG](https://github.com/wst24365888/XYZ-UML-Editor/blob/master/resource/screenshots/screenshot-1.PNG)

## About

This is the term project of the course **Object-Oriented Analysis and Design** (2021, National Central University).

## What's new in v2?

Nothing changed, but more OO.

## Feature

This is a simple UML editor with:

- Objects
  - Class
  - Use Case

- Relation Lines
  - Association Line
  - Generation Line
  - Composition Line

- Behavior
  - Create a new object
  - Rename an object
  - Drag an object
  - Connect between objects
  - Replace the connections between objects
  - Group objects
  - UnGroup objects

For detailed spec, [see this file](https://github.com/wst24365888/XYZ-UML-Editor/blob/master/resource/spec/spec.pdf).

## Abstract Class Diagram (v1)

![https://i.imgur.com/HlbBsKk.png](https://i.imgur.com/HlbBsKk.png)

## Part of Real Class Diagram (v1, Generated by IntelliJ)

![uml_diagram.png](https://github.com/wst24365888/XYZ-UML-Editor/blob/master/resource/uml_diagram/uml_diagram.png)

## Abstract Methods (v1)

- setCurrentMode(Mode): 設定當前模式
- setColor(Color): 設定按鈕顏色
- applyBehavior(): 向當前模式調用滑鼠事件觸發相對應的行為
- addComponent(JComponent): 將物件加入Canvas
- removeComponent(JComponent): 將物件從Canvas中移除
- setSourcePort(UMLObject): 把起點物件的reference還有Port資訊在Connection Line裡面設定好
- setDestinationPort(UMLObject): 把終點物件的reference還有Port資訊在Connection Line裡面設定好
- getUMLObjectInside(Position or Rectangle): 取得範圍內的UML Objects
- clearSelection(): 清除Canvas上當前的selection
- addSelection(UMLObject): 將UML Object加入Canvas上當前的Seleciton
- getSelection(): 取得Canvas上當前的selection
- setPortVisible(bool): 設定UML Object的Port是否可見
- setStartPosition(Position): Select Box的起始位置
- setEndPosition(Position): Select Box的終點位置
- drawSelectBox(Rectangle): 畫出選取範圍的框框
- showMenuItems(): 顯示Menu底下的選項
- addChild(UMLObject): 將UML Object加入Group Object
- getChildren(): 取得Group Object中的children
- moveTo(Position): 將UML Object移動到當前位置
- repaint(): 重畫Canvas
- setName(name): 設定UML Object的名字

## Use Cases in Abstract Sequence Diagram (v1)

![https://i.imgur.com/2L0NW9N.png](https://i.imgur.com/2L0NW9N.png)

![https://i.imgur.com/Gh0B2DC.png](https://i.imgur.com/Gh0B2DC.png)

![https://i.imgur.com/aqyUrl9.png](https://i.imgur.com/aqyUrl9.png)

![https://i.imgur.com/pCIVVHv.png](https://i.imgur.com/pCIVVHv.png)

![https://i.imgur.com/uLnBQEB.png](https://i.imgur.com/uLnBQEB.png)

![https://i.imgur.com/piis5wu.png](https://i.imgur.com/piis5wu.png)

![https://i.imgur.com/Kc5usn1.png](https://i.imgur.com/Kc5usn1.png)

![https://i.imgur.com/hOcs8WG.png](https://i.imgur.com/hOcs8WG.png)
