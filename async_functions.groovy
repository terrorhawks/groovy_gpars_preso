


def resize = { img -> img.resize(64,64)}
// make the block async
def fastResize = resize.async()
// each image in the collection is processed async in parallel
def resized = images.collect fastResize


