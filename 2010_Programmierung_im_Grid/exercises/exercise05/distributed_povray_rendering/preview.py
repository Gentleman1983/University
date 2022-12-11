#!/usr/bin/env python
import wx
import struct
import os, sys, time

# two different ways to load and display are given
# tested with Python24 and wxPython25   vegaseat   24jul2005

def readFromFile(fileName, dim):
  f = open(fileName,"rb")
  buf = f.read(18)
  line = struct.unpack("=3B2HB4H2B", buf)
        
  #print ("Dimension:", line[8:10]);
  #print ("Bits pro Pixel:", line[10]);

  im_byte_cnt = dim[0] * dim[1]
  im_bpp = line[10] // 8
  #print ("#Databytes exspected:", im_byte_cnt)

  im_byte_buf = ""
  for i in range(0, im_byte_cnt):
    buf = f.read(im_bpp)
    im_byte_buf += buf[2] + buf[1] + buf[0]
  #print ("#Databytes read:", len(im_byte_buf))

  f.close()

  return wx.BitmapFromBuffer(dim[0], dim[1], im_byte_buf)

class Panel1(wx.Panel):

  """ class Panel1 creates a panel with images on it, inherits wx.Panel """
  def __init__(self, *args, **kwargs):
    # create the panel
    wx.Panel.__init__(self, *args, **kwargs)
 
    self.fileList = []

    self.lines_per_part = full_dim[1] // parts
    self.remain = full_dim[1] % parts

    if self.lines_per_part == 0:
          self.parts = self.remain
          self.remain = 0
          self.lines_per_part = 1
    else:
          self.parts = parts

    self.timer = wx.Timer(self, -1)
    self.timer.Start(1000)
    self.Bind(wx.EVT_TIMER, self.OnTimer)
    
    for i in range(0, self.parts):
          index = format(i, "0>2")
          fileName = filePrefix+index+".tga"
          self.fileList.append((fileName, i))

  def OnTimer(self, event):
    try:
        # stop timer and try to get all finished files 
        self.timer.Stop()
        l = len (self.fileList)
        changed = False
        if l > 0:
          while l != 0:
            l = l-1
            f = self.fileList[l]
            fileName = f[0]
            i = f[1]            
            yStart, height = (0, 0)
            if os.path.exists(fileName+".finish"):
              if i > self.remain:
                height = self.lines_per_part
                yStart = (i) * (self.lines_per_part) + self.remain
              else:
                height = self.lines_per_part + 1
                yStart = (i) * (self.lines_per_part + 1)  
              img = readFromFile(fileName, (full_dim[0], height))
              wx.StaticBitmap(self, -1, img, (0, yStart), (full_dim[0], height))
              changed = True
              del self.fileList[l]
          if len (self.fileList) > 0:
            self.timer.Start(1000)
    except IOError:
        print "Image file %s not found" % imageFile
        raise SystemExit

filePrefix="pic_"+sys.argv[1]+"_"
full_dim = (int(sys.argv[2]), int(sys.argv[3]))
parts = int(sys.argv[4])
app = wx.PySimpleApp()

# create a window/frame, no parent, -1 is default ID
# increase the size of the frame for larger images
try:
  frame1 = wx.Frame(None, -1, "Preview", size = full_dim,
                    style =  wx.DEFAULT_FRAME_STYLE & ~ 
			    (wx.MINIMIZE_BOX | wx.RESIZE_BOX | wx.MAXIMIZE_BOX | wx.RESIZE_BORDER))
  # call the derived class
  Panel1(frame1, -1)
  frame1.Show(True)
  app.MainLoop()
finally:
  del app

