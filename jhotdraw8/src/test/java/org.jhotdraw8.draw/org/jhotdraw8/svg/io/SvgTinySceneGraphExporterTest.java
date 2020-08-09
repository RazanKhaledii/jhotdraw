/*
 * @(#)SvgTinySceneGraphExporterTest.java
 * Copyright © 2020 The authors and contributors of JHotDraw. MIT License.
 */

package org.jhotdraw8.svg.io;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jhotdraw8.annotation.NonNull;
import org.jhotdraw8.xml.XmlUtil;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.w3c.dom.Document;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class SvgTinySceneGraphExporterTest {
    @NonNull
    @TestFactory
    public List<DynamicTest> exportTestToWriterFactory() {
        return Arrays.asList(
                dynamicTest("rect", () -> testExportToWriter(new Rectangle(10, 20, 100, 200),
                        "<?xml version=\"1.0\" ?><svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" version=\"1.2\" baseProfile=\"tiny\">\n" +
                                "  <rect x=\"10\" y=\"20\" width=\"100\" height=\"200\" fill=\"#000000\"></rect>\n" +
                                "</svg>")),
                dynamicTest("text", () -> testExportToWriter(new Text(10, 20, "Hello"),
                        "<?xml version=\"1.0\" ?><svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" version=\"1.2\" baseProfile=\"tiny\">\n" +
                                "  <text font-family=\"'System Regular', 'System'\" font-size=\"13\" fill=\"#000000\"><tspan x=\"10\" y=\"20\">Hello</tspan></text>\n" +
                                "</svg>"))
        );
    }

    @NonNull
    @TestFactory
    public List<DynamicTest> exportTestToDOMFactory() {
        return Arrays.asList(
                dynamicTest("rect", () -> testExportToDOM(new Rectangle(10, 20, 100, 200),
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                                "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" baseProfile=\"tiny\" version=\"1.2\">\n" +
                                "  <rect fill=\"#000000\" height=\"200\" width=\"100\" x=\"10\" y=\"20\"/>\n" +
                                "</svg>\n")),
                dynamicTest("text", () -> testExportToDOM(new Text(10, 20, "Hello"),
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                                "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" baseProfile=\"tiny\" version=\"1.2\">\n" +
                                "  <text fill=\"#000000\" font-family=\"'System Regular', 'System'\" font-size=\"13\">\n" +
                                "    <tspan x=\"10\" y=\"20\">Hello</tspan>\n" +
                                "  </text>\n" +
                                "</svg>\n"))
        );
    }


    private void testExportToDOM(Node node, String expected) throws IOException {
        SvgTinySceneGraphExporter instance = new SvgTinySceneGraphExporter(null, null);
        Document document = instance.toDocument(node);
        StringWriter w = new StringWriter();
        XmlUtil.write(w, document);
        String actual = w.toString();
        assertEquals(expected, actual);
    }

    private void testExportToWriter(Node node, String expected) throws IOException {
        StringWriter w = new StringWriter();
        SvgTinySceneGraphExporter instance = new SvgTinySceneGraphExporter(null, null);
        instance.setPrettyPrint(true);
        instance.write(w, node);
        String actual = w.toString();
        assertEquals(expected, actual);
    }
}