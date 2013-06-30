
#!/usr/bin/python
#authors: Gray Marchiori-Simpson (gray.marchiori-simpson@metoffice.gov.uk), Rhodri Lewis Sykes (driagledd@github)

import sys, getopt
import xml.etree.ElementTree as eTree

# This function checks for help query
def checkHelp(argv):
   try:
      opts, args = getopt.getopt(argv,"hi:o:",["ifile=","ofile="])
   except getopt.GetoptError:
      print 'test.py -i <inputfile> -o <outputfile>'
      sys.exit(2)
   for opt, arg in opts:
      if opt == '-h':
         print 'test.py -i <inputfile> -o <outputfile>'
         sys.exit()

# This function gets the file name for input
def getInput(argv):
   inputfile = ''
   try:
      opts, args = getopt.getopt(argv,"hi:o:",["ifile=","ofile="])
   except getopt.GetoptError:
      print 'test.py -i <inputfile> -o <outputfile>'
      sys.exit(2)
   for opt, arg in opts:
      if opt in ("-i", "--ifile"):
         inputfile = arg
   return inputfile

# This function gets the file names for input and output
def getOutput(argv):
   outputfile = ''
   try:
      opts, args = getopt.getopt(argv,"hi:o:",["ifile=","ofile="])
   except getopt.GetoptError:
      print 'test.py -i <inputfile> -o <outputfile>'
      sys.exit(2)
   for opt, arg in opts:
      if opt in ("-o", "--ofile"):
         outputfile = arg
   return outputfile;

#This function grabs the data
def getValues(daysInMonth):
   gddsForYear = []
   #some code here
   gddsForYear.append(value)

#Procedure starts here
checkHelp(sys.argv[1:])
inputfile = getInput(sys.argv[1:])
outputfile = getOutput(sys.argv[1:])
print inputfile, outputfile

xmlSourceFile = eTree.parse(inputfile)
xmlTreeRoot = xmlSourceFile.getroot()
countLocs=0
maxLocs=0
for countByLoc in xmlTreeRoot.iter('loc'):
   maxLocs=maxLocs+1

jsonSinkFile = open(outputfile, 'w')
jsonSinkFile.write('{ "name": "Growing Degree Days 30 year", "description": "GDD over 30 years with 5 degree baseline smoothed over 30 years", "valueUnit": "GDD","locations": [ { ')
for runByLoc in xmlTreeRoot.iter('loc'):
   countDays=0
   countLocs=countLocs+1
   jsonSinkFile.write('"location": { ')
   for lon in runByLoc.iter('lon'):
      myLon = lon.text
   for lat in runByLoc.iter('lat'):
      myLat = lat.text
   jsonSinkFile.write('"loc" : [')
   jsonSinkFile.write(myLon)
   jsonSinkFile.write(',')
   jsonSinkFile.write(myLat)
   jsonSinkFile.write('], "dataPoints": [ ')
   for day in runByLoc.iter('day'):
      countDays=countDays+1
      jsonSinkFile.write('{ "dayOfYear": "')
      jsonSinkFile.write(str(countDays))
      jsonSinkFile.write('", "value": "')
      jsonSinkFile.write(day.text)
      jsonSinkFile.write('" }')
      if countDays<366: jsonSinkFile.write(',') 
   jsonSinkFile.write('] }')
   if countLocs<maxLocs: jsonSinkFile.write(',')
jsonSinkFile.write('} ] }')
jsonSinkFile.close()
