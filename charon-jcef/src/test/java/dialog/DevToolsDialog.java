// Copyright (c) 2014 The Chromium Embedded Framework Authors. All rights
// reserved. Use of this source code is governed by a BSD-style license that
// can be found in the LICENSE file.

package dialog;

import org.cef.browser.CefBrowser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class DevToolsDialog extends JDialog {
  private final CefBrowser devTools_;
  public DevToolsDialog(Frame owner, String title, CefBrowser browser) {
    this(owner, title, browser, null);
  }

  public DevToolsDialog(Frame owner, String title, CefBrowser browser,
      Point inspectAt) {
    super(owner, title, false);

    setLayout(new BorderLayout());
    setSize(800, 600);
    setLocation(owner.getLocation().x+20, owner.getLocation().y+20);

    devTools_ = browser.getDevTools(inspectAt);
    add(devTools_.getUIComponent());

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentHidden(ComponentEvent e) {
        dispose();
      }
    });
  }

  @Override
  public void dispose() {
    devTools_.close();
    super.dispose();
  }
}
