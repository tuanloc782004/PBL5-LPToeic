package com.pbl5.markdown;

import org.springframework.stereotype.Service;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

@Service
public class MarkdownServiceImpl implements MarkdownService {
    
	private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownServiceImpl() {
        this.parser = Parser.builder().build();
        this.renderer = HtmlRenderer.builder().build();
    }

    @Override
    public String convertMarkdownToHtml(String markdown) {
        if (markdown == null || markdown.isEmpty()) {
            return "";
        }
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }
    
}
