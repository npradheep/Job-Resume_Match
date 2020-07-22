from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
from io import StringIO
import urllib.request
import io




def getratio(jd,resume):
    resText = convertPDFToText(resume)
    toMatch=[resText,jd]
    cv = CountVectorizer()
    matrix = cv.fit_transform(toMatch)
    match = (cosine_similarity(matrix)[0][1])*100
    match = round(match,2)
    match = str(match)
    print(match)
    return match

def convertPDFToText(url):
    rsrcmgr = PDFResourceManager()
    retstr = StringIO()
    #codec = 'utf-8'
    laparams = LAParams()
    remote_file = urllib.request.urlopen(url).read()
    fp = io.BytesIO(remote_file)
    device = TextConverter(rsrcmgr, retstr, laparams=laparams)

    interpreter = PDFPageInterpreter(rsrcmgr, device)
    password = ""
    maxpages = 0
    caching = True
    pagenos = set()
    for page in PDFPage.get_pages(fp, pagenos, maxpages=maxpages, password=password, caching=caching,
                                  check_extractable=True):
        interpreter.process_page(page)
    fp.close()
    device.close()
    string = retstr.getvalue()
    retstr.close()
    return string



