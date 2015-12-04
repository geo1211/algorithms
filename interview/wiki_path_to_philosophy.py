import urllib2
import re


# returns # of steps in the path to Philosophy, if reachable
def path_to_philosophy( url, visited, steps=0 ):
    # path printing
    padding = ''
    for p in range(1, steps*2):
        padding += ' '
    print padding + url

    # infinite loop cuz of unhandled cases (such as Help:XXX, File:XXX)
    if url in visited:
        print 'infinite loop \n'
        return 0
    visited.append( url )

    # normal exit condition
    if url == 'Philosophy':
        return steps

    # get page
    page = urllib2.urlopen( URL_PREFIX + url )
    data = page.read()

    while len(data) > 0:
        paragraph = re.search(r'<p>.*</p>', data)
        if not paragraph:
            print 'no paragraph \n'
            return 0  # no more paragraph
        
        # for parenthese enclose check
        pstr_orig = paragraph.group()
        pstr = pstr_orig
        pstr_index_of_orig = 0

        while len(pstr) > 0:
            ahref = re.search(r'<a href=\"/wiki/([^"]+)\"', pstr)
            if not ahref:
                data = data[ paragraph.end(): ]  # next paragraph
                break

            astr = ahref.group()
            italized = pstr.find('<i>'+astr)
            if italized == -1:

                # aa_str_index_of_orig -> 0, see if net ( is > 0
                aa_str_index_of_orig = pstr_orig.find( astr, pstr_index_of_orig )
                paren_net = 0
                for pp in range(aa_str_index_of_orig, -1, -1): # end at -1 cuz exclusive
                    if pstr_orig[pp] == '(':
                        paren_net += 1
                    elif pstr_orig[pp] == ')':
                        paren_net -= 1
                if paren_net <= 0:  # no pending open
                    return path_to_philosophy( ahref.group(1), visited, steps+1 )  # recurive result

                # this </a> -> pstr_orig.length-1, see if net ) is > 0
                rstart = pstr.find('</a>', pstr.find(astr))
                paren_net = 0
                for pp in range(rstart, len(pstr)):
                    if pstr_orig[pp] == '(':
                        paren_net += 1
                    elif pstr_orig[pp] == ')':
                        paren_net -= 1
                if paren_net >= 0:  # no pending close
                    return path_to_philosophy( ahref.group(1), visited, steps+1 )  # recurive result

            # italized / parenthesed;  move on -> next ahref inside paragraph
            pstr = pstr[ ahref.end(): ]
            pstr_index_of_orig += ahref.end()



# python 3.4 has built-in 
def median(lst):
    lst = sorted(lst)
    if len(lst) < 1:
            return None
    if len(lst) %2 == 1:
            return lst[((len(lst)+1)/2)-1]
    else:
            return float(sum(lst[(len(lst)/2)-1:(len(lst)/2)+1]))/2.0


# program starts here
#  e.g.  <i><a href="/wiki/Zeitgeist" title="Zeitgeist">zeitgeist</a></i>
#  e.g.  (see <a href="/wiki/Aesthetics" title="Aesthetics">aesthetics</a>)

URL_PREFIX = 'https://en.wikipedia.org/wiki/'
NUM_URLS = 500
arrived_steps = []

for ind in range(1, NUM_URLS+1):
    url_random = 'Special:Random'
    #url_random = 'Art'
    #url_random = 'Districts_of_Yemen'
    #url_random = 'Glyph'
    visited = []
    steps = path_to_philosophy( url_random, visited )
    if (steps > 0):
        arrived_steps.append(steps)
        print 'steps: ' + str(steps)
        print ''

# percentage, median, average
print 'percentage to Philosophy: ' + str(100.0*len(arrived_steps)/NUM_URLS)
print 'path length average: ' + str(1.0*sum(arrived_steps)/len(arrived_steps))
print 'path length median:  ' + str(median(arrived_steps))


# to reduce the number of http requests for 500 random pages,
# change the implementation to keep track of all URLs' path lengths,
# so as to save a lot of HTTP calls + string parsing because
# the last few steps before reaching Philosophy are usually the same,
# thus saving a lot of calls
