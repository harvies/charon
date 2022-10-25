// reserved. Use of this source code is governed by a BSD-style license that
// can be found in the LICENSE file.

import org.cef.OS;
import org.cef.browser.CefBrowser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class WebPanel extends JPanel {

    private final JButton backButton_;
    private final JButton forwardButton_;
    private final JButton reloadButton_;
    private final CefBrowser browser_;

    public WebPanel(CefBrowser browser) {
        browser_ = browser;
        setEnabled(browser_ != null);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(Box.createHorizontalStrut(5));

        backButton_ = new JButton("Back");
        backButton_.setAlignmentX(LEFT_ALIGNMENT);
        backButton_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browser_.goBack();
            }
        });
        add(backButton_);
        add(Box.createHorizontalStrut(5));

        forwardButton_ = new JButton("Forward");
        forwardButton_.setAlignmentX(LEFT_ALIGNMENT);
        forwardButton_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browser_.goForward();
            }
        });
        add(forwardButton_);
        add(Box.createHorizontalStrut(5));

        reloadButton_ = new JButton("Reload");
        reloadButton_.setAlignmentX(LEFT_ALIGNMENT);
        reloadButton_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reloadButton_.getText().equalsIgnoreCase("reload")) {
                    int mask = OS.isMacintosh()
                            ? ActionEvent.META_MASK
                            : ActionEvent.CTRL_MASK;
                    if ((e.getModifiers() & mask) != 0) {
                        System.out.println("Reloading - ignoring cached values");
                        browser_.reloadIgnoreCache();
                    } else {
                        System.out.println("Reloading - using cached values");
                        browser_.reload();
                    }
                } else {
                    browser_.stopLoad();
                }
            }
        });
        add(reloadButton_);
        add(Box.createHorizontalStrut(5));
    }

    public void update(CefBrowser browser, boolean isLoading, boolean canGoBack, boolean canGoForward) {
        if (browser == browser_) {
            backButton_.setEnabled(canGoBack);
            forwardButton_.setEnabled(canGoForward);
            reloadButton_.setText(isLoading ? "Abort" : "Reload");
        }
    }
}
