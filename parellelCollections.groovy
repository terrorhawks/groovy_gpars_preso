
def favoriteQuote = [lufthansa, ba, airfrance, delta].collectParallel {
	it.askForQuote myTrip
}

Fork / Join algorithms essentially split a problem at hands into several smaller sub-problems and recursively apply the same algorithm to each of the sub-problems