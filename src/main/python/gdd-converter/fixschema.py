
#!/usr/bin/python
#authors: Gray Marchiori-Simpson (gray.marchiori-simpson@metoffice.gov.uk), Rhodri Lewis Sykes (driagledd@github)

import sys, getopt

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

xmlSourceFile = open(inputfile, 'r')
xmlSinkFile = open(outputfile, 'w')
for line in xmlSourceFile:
   #our code goes here
   value=line
   value=value.replace('10>','day>')
   value=value.replace('11>','day>')
   value=value.replace('12>','day>')
   value=value.replace('13>','day>')
   value=value.replace('14>','day>')
   value=value.replace('15>','day>')
   value=value.replace('16>','day>')
   value=value.replace('17>','day>')
   value=value.replace('18>','day>')
   value=value.replace('19>','day>')
   value=value.replace('20>','day>')
   value=value.replace('21>','day>')
   value=value.replace('22>','day>')
   value=value.replace('23>','day>')
   value=value.replace('24>','day>')
   value=value.replace('25>','day>')
   value=value.replace('26>','day>')
   value=value.replace('27>','day>')
   value=value.replace('28>','day>')
   value=value.replace('29>','day>')
   value=value.replace('30>','day>')
   value=value.replace('31>','day>')
   value=value.replace('1>','day>')
   value=value.replace('2>','day>')
   value=value.replace('3>','day>')
   value=value.replace('4>','day>')
   value=value.replace('5>','day>')
   value=value.replace('6>','day>')
   value=value.replace('7>','day>')
   value=value.replace('8>','day>')
   value=value.replace('9>','day>')
   xmlSinkFile.write(value)
xmlSourceFile.close()
xmlSinkFile.close()
      

