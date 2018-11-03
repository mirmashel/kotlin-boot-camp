package io.rybalkinsd.kotlinbootcamp.geometry

import kotlin.math.max
import kotlin.math.min

/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider {
    fun isColliding(other: Collider): Boolean
}

/**
 * 2D point with integer coordinates
 */
class Point(val x: Int, val y: Int) : Collider {
    override fun isColliding(other: Collider): Boolean {
        if (other is Point) return (x == other.x) and (y == other.y)
        else if (other is Bar) return other.point_in_bar(this)
        return false
    }

    override fun equals(other: Any?): Boolean = if (other is Point) (x == other.x) and (y == other.y) else false
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
class Bar(firstCornerX: Int, firstCornerY: Int, secondCornerX: Int, secondCornerY: Int) : Collider {
    var leftDownCornerX = min(firstCornerX, secondCornerX)
    var leftDownCornerY = min(firstCornerY, secondCornerY)
    var rightUpCornerX = max(firstCornerX, secondCornerX)
    var rightUpCornerY = max(firstCornerY, secondCornerY)

    fun point_in_bar(p: Point): Boolean {
        return (leftDownCornerX <= p.x) and (p.x <= rightUpCornerX) and (leftDownCornerY <= p.y) and
                (p.y <= rightUpCornerY)
    }

    override fun isColliding(other: Collider): Boolean {
        if (other is Point) return point_in_bar(other)
        else if (other is Bar) {
            return point_in_bar(Point(other.leftDownCornerX, other.leftDownCornerY)) ||
                    point_in_bar(Point(other.rightUpCornerX, other.rightUpCornerY)) ||
                    other.point_in_bar(Point(leftDownCornerX, rightUpCornerY)) ||
                    other.point_in_bar(Point(rightUpCornerX, leftDownCornerY))
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        if (other is Bar)
            return (other.rightUpCornerY == rightUpCornerY) and (other.rightUpCornerX == rightUpCornerX) and
                    (other.leftDownCornerY == leftDownCornerY) and (other.leftDownCornerX == leftDownCornerX)
        return false
    }
}