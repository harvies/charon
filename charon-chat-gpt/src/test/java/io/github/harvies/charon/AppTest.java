package io.github.harvies.charon;

import static org.junit.Assert.assertTrue;

import com.github.plexpt.chatgpt.Chatbot;
import org.junit.Test;

import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Chatbot chatbot = new Chatbot("","cf_clearance","user-agent");
        Map<String, Object> chatResponse = chatbot.getChatResponse("hello");
        System.out.println(chatResponse.get("message"));
    }
}
